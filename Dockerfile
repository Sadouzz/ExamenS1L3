# =========================
# BUILD STAGE
# =========================
FROM mcr.microsoft.com/dotnet/sdk:9.0 AS build
WORKDIR /src

# Copier le csproj et restaurer les dépendances
COPY CSharp/CSharp.csproj CSharp/
RUN dotnet restore CSharp/CSharp.csproj

# Copier tout le reste
COPY . .
WORKDIR /src/CSharp

# Build en Release
RUN dotnet publish -c Release -o /app/publish /p:UseAppHost=false


# =========================
# RUNTIME STAGE
# =========================
FROM mcr.microsoft.com/dotnet/aspnet:9.0 AS runtime
WORKDIR /app

# Copier les fichiers publiés
COPY --from=build /app/publish .

# Configuration ASP.NET
ENV ASPNETCORE_URLS=http://+:8080
ENV ASPNETCORE_ENVIRONMENT=Production

EXPOSE 8080

# Lancer l'application
ENTRYPOINT ["dotnet", "CSharp.dll"]
