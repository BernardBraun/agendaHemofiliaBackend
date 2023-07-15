# Use a imagem base do Gradle com JDK 17
FROM gradle:jdk17

# Defina o diret�rio de trabalho dentro do cont�iner
WORKDIR /app

# Copie os arquivos de configura��o e c�digo-fonte para o diret�rio de trabalho
COPY build.gradle .
COPY settings.gradle .
COPY src ./src

# Execute o comando Gradle para construir a aplica��o
RUN gradle build -x test

EXPOSE 8080

# Defina o comando de inicializa��o da aplica��o
CMD ["java", "-jar", "build/libs/api-hemofilia-0.0.1-SNAPSHOT.jar"]


### Comando para gerar a imagem ###

#docker build -t api-hemofilia .

### Gerando o container de execu��o da api ###

#docker run -d --name cont-api-hemo -p 8080:8080 -e db_host=ip_host -e db_port=3306 -e db_data_base_name=app_hemofilia -e db_user=root -e db_pass=senha -e jwt_secret=api api-hemofilia:latest