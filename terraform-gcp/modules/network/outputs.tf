output "network" {
  value = google_compute_network.network.name
}

output "subnetwork" {
  value = google_compute_subnetwork.subnet.name
}

output "dependency" {
  value = google_compute_subnetwork.subnet.self_link
}
