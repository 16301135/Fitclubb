from django import forms
import re
from .models import User
from django.core.validators import EmailValidator



#用户注册表单
class UserCreationForm(forms.ModelForm):


    name = forms.CharField(label='username',max_length=254,required=True, widget=forms.TextInput(attrs={'class':'form-control'}))
    password1 = forms.CharField(label='Password', widget=forms.PasswordInput(attrs={'class':"form-control"}))
    password2 = forms.CharField(label = 'Password Confirmation', widget=forms.PasswordInput(attrs={'class':"form-control"}))
    class Meta:
        #注册表单字段
        model = User
        fields = ('name','password1','password2',)
    def clean_password2(self):
        password1 = self.cleaned_data.get("password1")
        password2 = self.cleaned_data.get("password2")
        if password1 and password2 and password1 != password2:
            raise forms.ValidationError("Password mismatch!")
        return password2




    def clean_name(self):
        name = self.cleaned_data.get('name')

        filter_result = User.objects.filter(name=name)
        if len(filter_result)>0:
            raise forms.ValidationError("username already taken")


        return name


    def clean_password1(self):
        password1 = self.cleaned_data.get("password1")
        if len(password1) < 6:
            raise forms.ValidationError("Password too short ")
        return password1

#用户登录表单
class LoginForm(forms.Form):

    name = forms.CharField(label = 'name')
    password = forms.CharField(label = 'Password', widget=forms.PasswordInput)

    #Use clean methods to define custom validation rules
    def clean_name(self):
        name = self.cleaned_data.get('name')


        filter_result = User.objects.filter(name=name)
        if len(filter_result) <= 0:
            raise forms.ValidationError("username not found")

        return name