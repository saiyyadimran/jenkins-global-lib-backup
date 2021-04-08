FROM openjdk:17-ea-10-jdk
RUN mkdir /src
WORKDIR /app
RUN pwd
ADD build/python/app/* /app/
RUN ls -ltr
RUN pip install -r requirements.txt
EXPOSE 5000
CMD ["python", "/app/main.py"]