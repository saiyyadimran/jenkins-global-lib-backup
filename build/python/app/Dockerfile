FROM python:rc-alpine3.12
RUN mkdir /app
RUN ls -ltr
ADD * /app/
WORKDIR /app
RUN pwd
RUN pip install -r python/app/requirements.txt
EXPOSE 5000
CMD ["python", "python/app/main.py"]