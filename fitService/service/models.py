from django.db import models
# from django.contrib.auth.models import AbstractUser
from django.contrib.auth.base_user import AbstractBaseUser
from django.contrib.auth.base_user import BaseUserManager


# Create your models here.
class UserManager(BaseUserManager):

    def create_user(self, name, password=None):
        # create user here
        if not name:
            raise ValueError('Users must have an username')

        user = self.model(

            name=name

        )
        user.set_password(password)
        user.save(using=self._db)
        return user

    def create_superuser(self, name,created_at, password):
        # create superuser here
        user = self.create_user(
           name = name,
            password=password,
        )
        user.is_admin = True
        user.save(using=self._db)
        return user


class User(AbstractBaseUser):

    name = models.CharField(unique=True, max_length=255, )
    password = models.CharField(max_length=30)
    created_at = models.DateTimeField(auto_now_add=True)
    is_active = models.BooleanField(default=True)
    is_admin = models.BooleanField(default=False)
    objects = UserManager()  # 创建用户

    USERNAME_FIELD = 'name'
    Required_fields = ['created_at']

    def get_full_name(self):
        return self.name

    def get_short_name(self):
        return self.name

    def __str__(self):
        return self.name
