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


class Requisition(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    WFO = '等待接单'
    WFR = '等待维修'
    COMP = '维修完成'
    STATUS = (
        (WFO, 'Waiting for orders'),
        (WFR, 'Waiting for repair'),
        (COMP, 'Repair completed'),
    )
    status = models.CharField(choices=STATUS, default=WFO, max_length=18)
    content = models.CharField(max_length=256)
    user = models.ForeignKey(User, on_delete=models.CASCADE)

    def __str__(self):
        return self.content

    class Meta:
        db_table = 'requisition'
        verbose_name = "报修单"
        verbose_name_plural = verbose_name


class Order(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    user = models.ForeignKey(User, on_delete=models.CASCADE)
    price = models.IntegerField()
    items = models.CharField(max_length=256)

    def __str__(self):
        return self.items

    class Meta:
        db_table = 'order'
        verbose_name = "订单"
        verbose_name_plural = verbose_name


# class Food(models.Model):
#     name = models.CharField(primary_key=True, max_length=100)
#     price = models.IntegerField()
#     # reception =
