import uuid

from django.contrib.auth.models import User
from django.db import models

# Create your models here.
# python manage.py makemigrations
# python manage.py migrate
# python manage.py sqlmigrate project 0002


# class User(models.Model):
#     username = models.CharField(max_length=128, unique=True)
#     password = models.CharField(max_length=256)
#
#     def __str__(self):
#         return self.username
#
#     class Meta:
#         verbose_name = "用户"
#         verbose_name_plural = verbose_name


class Order(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    WFO = 'Waiting for orders'
    WFR = 'Waiting for repair'
    COMP = 'Repair completed'
    STATUS = (
        (WFO, '等待接单'),
        (WFR, '等待维修'),
        (COMP, '维修完成'),
    )
    status = models.CharField(choices=STATUS, default=WFO, max_length=18)
    content = models.CharField(max_length=256)
    user = models.ForeignKey(User, on_delete=models.CASCADE)

    def __str__(self):
        return self.content

    class Meta:
        verbose_name = "订单"
        verbose_name_plural = verbose_name
