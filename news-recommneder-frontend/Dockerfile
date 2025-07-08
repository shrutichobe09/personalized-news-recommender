# ---- Build React App ----
FROM node:18-alpine as build

WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .
RUN npm run build

# ---- Serve using NGINX ----
FROM nginx:stable-alpine

# Custom nginx config for React Router
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Copy built React files
COPY --from=build /app/build /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
