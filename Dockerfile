FROM oraclelinux:8 as builder

# Since the files are compressed as tar.gz first dnf install tar. gzip is already in oraclelinux:8
RUN dnf install -y tar

# Default to UTF-8 file.encoding
ENV LANG en_US.UTF-8

# Environment variables for the builder image.
# Required to validate that you are using the correct file

ENV JAVA_URL=https://download.oracle.com/java/17/latest \
	JAVA_HOME=/usr/java/jdk-17

##
SHELL ["/bin/bash", "-o", "pipefail", "-c"]
RUN set -eux; \
	ARCH="$(uname -m)" && \
	# Java uses just x64 in the name of the tarball
    if [ "$ARCH" = "x86_64" ]; \
        then ARCH="x64"; \
    fi && \
    JAVA_PKG="$JAVA_URL"/jdk-17_linux-"${ARCH}"_bin.tar.gz ; \
	JAVA_SHA256="$(curl "$JAVA_PKG".sha256)" ; \
	curl --output /tmp/jdk.tgz "$JAVA_PKG" && \
	echo "$JAVA_SHA256" */tmp/jdk.tgz | sha256sum -c; \
	mkdir -p "$JAVA_HOME"; \
	tar --extract --file /tmp/jdk.tgz --directory "$JAVA_HOME" --strip-components 1

## Get a fresh version of OL8 for the final image
FROM oraclelinux:8

# Default to UTF-8 file.encoding
ENV LANG en_US.UTF-8
ENV	JAVA_HOME=/usr/java/jdk-17
ENV	PATH $JAVA_HOME/bin:$PATH

# If you need the Java Version you can read it from the release file with
# JAVA_VERSION=$(sed -n '/^JAVA_VERSION="/{s///;s/"//;p;}' "$JAVA_HOME"/release);

# Copy the uncompressed Java Runtime from the builder image
COPY --from=builder $JAVA_HOME $JAVA_HOME

RUN set -eux; \
# Ensure we get the latest OL 8 updates available at build time
	dnf -y update; \
# JDK assumes freetype is available
	dnf install -y \
		freetype fontconfig \
	; \
	rm -rf /var/cache/dnf; \
	ln -sfT "$JAVA_HOME" /usr/java/default; \
	ln -sfT "$JAVA_HOME" /usr/java/latest; \
	for bin in "$JAVA_HOME/bin/"*; do \
		base="$(basename "$bin")"; \
		[ ! -e "/usr/bin/$base" ]; \
		alternatives --install "/usr/bin/$base" "$base" "$bin" 20000; \
	done;

CMD ["jshell"]