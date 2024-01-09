#!/bin/bash

#cd k8s

echo "Iniciando aplicação fastfood-production..."

# Aplica secrets
kubectl apply -f fastfood-production-mongo-secrets.yml &&
kubectl apply -f fastfood-production-rabbit-secrets.yml &&
kubectl apply -f fastfood-production-secrets.yml &&

# Aplica serviços
kubectl apply -f fastfood-production-mongo-svc.yml &&
kubectl apply -f fastfood-production-rabbit-svc.yml &&
kubectl apply -f fastfood-production-svc.yml &&

# Aplica StatefulSet
kubectl apply -f fastfood-production-mongo-statefulset.yml &&
kubectl apply -f fastfood-production-rabbit-statefulset.yml &&

# Aplica Deployment
kubectl apply -f fastfood-production-deployment.yml &&

# Aplica HorizontalPodAutoscaler (HPA)
#kubectl apply -f fastfood-hpa.yml &&

# Aplica componentes
#kubectl apply -f components.yaml

echo "Aplicação inicializada!"