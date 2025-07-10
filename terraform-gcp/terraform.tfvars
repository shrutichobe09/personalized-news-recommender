project_id   = "rosy-resolver-465404-b2"
region       = "us-central1"
zone         = "us-central1-a"
cluster_name = "news-gke-cluster"

# Network
#vpc_name    = "news-vpc"
network_name = "news-vpc"
subnet_name = "news-subnet"
subnet_cidr  = "10.0.0.0/16"

#gke
services_range_name = "gke-services-range"
pods_range_name     = "gke-pods-range"


# IAM
account_id   = "gke-app-sa"
display_name = "GKE App Service Account"
iam_roles = [
  "roles/storage.admin",
  "roles/logging.logWriter",
  "roles/container.nodeServiceAccount",
  "roles/container.clusterViewer"  
]
