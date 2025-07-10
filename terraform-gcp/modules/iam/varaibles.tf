variable "project_id" {
  description = "GCP Project ID"
  type        = string
}

variable "account_id" {
  description = "Service account ID (unique)"
  type        = string
}

variable "display_name" {
  description = "Display name of the service account"
  type        = string
}

variable "roles" {
  description = "List of IAM roles to assign"
  type        = list(string)
}
