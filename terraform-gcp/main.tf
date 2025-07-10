module "network" {
  source        = "./modules/network"
  region        = var.region
  network_name  = var.network_name
  subnet_name   = var.subnet_name
  subnet_cidr   = var.subnet_cidr
  pods_range_name     = var.pods_range_name
  services_range_name = var.services_range_name
}

module "iam" {
  source       = "./modules/iam"
  project_id   = var.project_id
  account_id   = var.account_id
  display_name = var.display_name
  roles        = var.iam_roles
}

module "gke" {
  source                   = "./modules/gke"
  project_id               = var.project_id
  location                 = var.zone
  cluster_name             = var.cluster_name
  network                  = module.network.network
  subnetwork               = module.network.subnetwork
  services_range_name      = var.services_range_name
  pods_range_name          = var.pods_range_name
  network_module_dependency = module.network.dependency
}
