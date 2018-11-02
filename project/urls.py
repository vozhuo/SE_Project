from django.conf.urls import url
from django.urls import path
from . import views

urlpatterns = [
    path('', views.index, name='index'),
    path('login/', views.login_view, name='login'),
    path('logout/', views.logout_view, name='logout'),
    path('login.do', views.login_action, name='loginAction'),
    path('user_info/', views.change_password, name='change'),
    path('password_reset/', views.PasswordResetView.as_view(), name='reset'),
    path('password_reset_done/', views.PasswordResetDoneView.as_view(), name='reset_done'),
    url(r'^reset/(?P<uidb64>[0-9A-Za-z_\-]+)/(?P<token>[0-9A-Za-z]{1,13}-[0-9A-Za-z]{1,20})/$',
        views.PasswordResetConfirmView.as_view(), name='reset_confirm'),

    path('password_reset_complete/', views.reset_complete, name='reset_complete'),

    path('order/add/', views.order_add, name='add_order'),
    path('order/list/', views.OrderListView.as_view(), name='order_list'),

    path('requisition/add/', views.requisition_add, name='add_requisition'),
    path('requisition/list/', views.RequisitionListView.as_view(), name='requisition_list')
]
