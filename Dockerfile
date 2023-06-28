FROM ubuntu:23.10

# Instala OpenJDK 17
RUN apt-get update && apt-get install -y openjdk-17-jdk

# Configura las variables de entorno para el JDK
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV PATH=$PATH:$JAVA_HOME/bin

# Copia tu aplicación al contenedor
COPY my-restservice.jar /app/my-restservice.jar

# Establece el directorio de trabajo
WORKDIR /app

# Exponer el puerto del contenedor (si es necesario)
EXPOSE 8080

# Comando de inicio del servicio
CMD ["java", "-jar", "my-restservice.jar"]