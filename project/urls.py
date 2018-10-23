from django.conf.urls import url
from django.urls import path
from django.contrib.auth.views import PasswordResetView
from . import views

urlpatterns = [
    path('', views.index, name='index'),
    path('login/', views.login_view, name='login'),
    path('logout/', views.logout_view, name='logout'),  #(?P<quit_hash>[\w\d]+)/$
    path('login.do', views.login_action, name='loginAction'),
    path('user_info/', views.change_password, name='change'),
    path('password_reset/', views.PasswordResetView.as_view(), name='reset'),
    path('password_reset_done/', views.PasswordResetDoneView.as_view(), name='reset_done'),
    path('reset/<uuid:uidb64>/<slug:token>/', views.PasswordResetConfirmView.as_view(), name='reset_confirm'),

    path('order/add/', views.order_add, name='add_order'),
    path('order/list/', views.OrderListView.as_view(), name='order_list')
]
