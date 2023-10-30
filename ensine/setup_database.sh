#!/bin/bash

set -e

echo "Script de instalação para a EC2 database"
echo ""
sleep 2

echo "Configuração EC2"
sleep 1
echo ""
sudo apt update

echo ""
echo "Verificando instalação do docker..."
sleep 1
echo ""
if !command -v docker &> /dev/null; then
	echo "Docker não está instalado. Instalando docker.io..."
	sudo apt install -y docker.io
	sudo systemctl start docker
	sudo systemctl enable docker
else
	echo "Docker já instalado."
fi

#echo "Clonando repositório do github..."
#sleep 1
#read -p "Username github: " username
#read -s -p "Token github: " token
#
#if [ ! -d "Database" ]; then
#	git clone https://$username:$token@github.com/ensine-me/Database.git
#else
#	echo "Repositório já clonado."
#fi
#sleep 1
#
#unset username
#unset token

echo "Entrando no repositório e iniciando configuração do container..."
sleep 1
cd scripts || exit
if [ ! -f "Dockerfile" ]; then
	echo "Dockerfile não encontrado."
	exit 1
fi

echo ""
echo "construindo imagem docker..."
sudo docker build -t ensine-database-image .
echo ""
echo "Iniciando container docker..."
sleep 1
sudo docker run --name ensine-database-container -p 3306:3306 -d ensine-database-image

echo "Containers ativos:"
sudo docker ps -a

echo ""
sleep 1
echo "Script finalizado!"
