FROM node:14

# Create app directory
RUN pwd
RUN ls -ltr
COPY * /usr/src/app/
WORKDIR /usr/src/app/node

# Install app dependencies
# A wildcard is used to ensure both package.json AND package-lock.json are copied
# where available (npm@5+)
RUN pwd
RUN ls -ltr
RUN npm install
# If you are building your code for production
# RUN npm ci --only=production

# Bundle app source
COPY . .

EXPOSE 8080
CMD [ "node", "server.js" ]