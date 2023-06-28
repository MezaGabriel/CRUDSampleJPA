FROM wildfly:12.0.0.Final

# Define las variables de entorno para la versión de Java
ENV JAVA_VERSION 17

# Instala la versión de Java deseada
RUN curl -O https://download.java.net/java/GA/jdk${JAVA_VERSION}/openjdk-${JAVA_VERSION}_linux-x64_bin.tar.gz \
    && tar -xzf openjdk-${JAVA_VERSION}_linux-x64_bin.tar.gz -C /opt \
    && rm openjdk-${JAVA_VERSION}_linux-x64_bin.tar.gz

# Configura las variables de entorno para el JDK
ENV JAVA_HOME /opt/jdk-${JAVA_VERSION}
ENV PATH $JAVA_HOME/bin:$PATH

# Copia tu aplicación al directorio de despliegue de WildFly
COPY my-restservice.war /opt/jboss/wildfly/standalone/deployments/

# Exponer el puerto del contenedor (si es necesario)
EXPOSE 8080

# Comando de inicio del servidor WildFly
CMD ["standalone.sh", "-b", "0.0.0.0"]