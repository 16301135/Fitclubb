from django.conf.urls import url
from .import views
app_name = 'books'#命名空间
urlpatterns = [
    url(r'^register$',views.register,name='register'),

    url(r'^login$',views.login),
    url(r'^myname',views.myname)
]