from django.shortcuts import render

import json
from django.shortcuts import render,redirect,get_object_or_404
from .forms import UserCreationForm, LoginForm
from django.contrib import auth
from django.http import HttpResponse
from service.models import User
from django.contrib.auth.backends import ModelBackend
# Create your views here.
# 后端验证用户真伪
class CustomBackend(ModelBackend):
    def authenticate(self, request, username=None, password=None):
        try:
            user = User.objects.get(name=username)
            # 先差user 然后调用方法去比较密码
            if user.check_password(password):
                return user
        except Exception as e:
            return None


def register(request):
    if request.method =='POST':
        form = UserCreationForm(request.POST)
        if form.is_valid():
            name = form.cleaned_data['name']

            password = form.cleaned_data['password2']
        #使用user自带的create_user方法创建用户，不需要使用save()
            user = User.objects.create_user(name=name, password=password)
            #给客户端return一个json对象
            return HttpResponse(json.dumps({"status":1,
                               "content":{
                                   "username":name
                               }}),content_type="application/json",charset="utf-8")
        else:
            return HttpResponse(json.dumps({"status": 0,
                                            "content": {
                                                "username": None
                                            }}), content_type="application/json", charset="utf-8")
    else:
        return HttpResponse(json.dumps({"status": 0,
                                        "content": {
                                            "username": None
                                        }}), content_type="application/json", charset="utf-8")


def login(request):
    if request.method =='POST':
        form = LoginForm(request.POST)
        if form.is_valid():
            name = form.cleaned_data['name']
            password = form.cleaned_data['password']
            user = auth.authenticate(username=name, password=password)
            if user is not None and user.is_active:
                auth.login(request,user)
                return HttpResponse(json.dumps({"status": 1,
                                                "content": {
                                                    "username": name
                                                }}), content_type="application/json", charset="utf-8")
            else:
                return HttpResponse(json.dumps({"status": 0,
                                                "content": {
                                                    "username": None
                                                }}), content_type="application/json", charset="utf-8")
        return HttpResponse(json.dumps({"status": 2,
                                        "content": {
                                            "username": None
                                        }}), content_type="application/json", charset="utf-8")
    return HttpResponse(json.dumps({"status": 0,
                                    "content": {
                                        "username": None
                                    }}), content_type="application/json", charset="utf-8")

def myname(request):
    if request.user.is_authenticated:
        return HttpResponse(json.dumps({"status": 1,
                                        "content": {
                                            "username": request.user.name
                                        }}), content_type="application/json", charset="utf-8")
    else:
        return HttpResponse(json.dumps({"status": 0,
                                        "content": {
                                            "username": None
                                        }}), content_type="application/json", charset="utf-8")