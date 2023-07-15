# Use a imagem base do Gradle com JDK 17
FROM gradle:jdk17

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie os arquivos de configuração e código-fonte para o diretório de trabalho
COPY build.gradle .
COPY settings.gradle .
COPY src ./src

# Execute o comando Gradle para construir a aplicação
RUN gradle build -x test

EXPOSE 8080

# Defina o comando de inicialização da aplicação
CMD ["java", "-jar", "build/libs/api-hemofilia-0.0.1-SNAPSHOT.jar"]


### Comando para gerar a imagem ###

#docker build -t api-hemofilia .

### Gerando o container de execução da api ###

#docker run -d --name cont-api-hemo -p 8080:8080 -e db_host=ip_host -e db_port=3306 -e db_data_base_name=app_hemofilia -e db_user=root -e db_pass=senha -e jwt_secret=api api-hemofilia:latest