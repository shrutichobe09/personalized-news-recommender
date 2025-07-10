variable "project_id" {
  type = string
}

variable "region" {
  type = string
}

variable "zone" {
  type = string
}

variable "cluster_name" {
  type = string
}



variable "network_name" {
  description = "Name of the VPC network"
  type        = string
}

variable "subnet_name" {
  type = string
}
variable "subnet_cidr" {
  description = "CIDR block for the subnet"
  type        = string
}



# IAM
variable "account_id" {
  type = string
}

variable "display_name" {
  type = string
}

variable "iam_roles" {
  type = list(string)
}
variable "services_range_name" {
  description = "Name of the secondary range for services"
  type        = string
}

variable "pods_range_name" {
  description = "Name of the secondary range for pods"
  type        = string
}