from django.contrib.auth.decorators import login_required
from django.contrib.auth import authenticate, login, logout
from django.contrib.auth.forms import PasswordResetForm
from django.http import HttpResponseRedirect
from django.shortcuts import render, redirect
from django.contrib.auth.models import User
from django.core.mail import send_mail
from django.contrib.auth import views
from django.views import generic
# Create your views here.
from django.urls import reverse_lazy

from project.models import Order
from . import models


@login_required
def index(request):
    # if not request.user.is_authenticated:
    #     return redirect('login')
    return render(request, 'project/index.html')


def login_action(request):
    username = request.POST['username']
    password = request.POST['password']
    user = authenticate(request, username=username, password=password)
    if user is not None:
        login(request, user)
        return redirect('/')
    else:
        return redirect('login')


@login_required
def logout_view(request):
    logout(request)
    return HttpResponseRedirect('/login')


def login_view(request):
    if request.user.is_authenticated:
        return redirect('/')
    return render(request, 'project/user/login.html')


def change_password(request):
    if request.method == 'POST':
        old_password = request.POST['oldPassword']
        new_password = request.POST['newPassword']
        user = User.objects.get(username=request.user.username)
        if user.check_password(old_password):
            if len(new_password) == 0:
                return render(request, 'project/user/user_info.html', {'length_error': '密码不能为空'})
            user.set_password(new_password)
            user.save()
            return render(request, 'project/user/user_info.html', {'success': '密码修改成功'})
        else:
            return render(request, 'project/user/user_info.html', {'error': '旧密码错误'})
    return render(request, 'project/user/user_info.html')


class PasswordResetView(views.PasswordResetView):
    form_class = PasswordResetForm
    template_name = 'project/reset.html'
    success_url = reverse_lazy('password_reset_done/')
    email_template_name = 'project/reset_email.html'
    subject_template_name = 'project/subject.txt'


class PasswordResetDoneView(views.PasswordResetDoneView):
    template_name = 'project/reset_done.html'


class PasswordResetConfirmView(views.PasswordResetConfirmView):
    template_name = 'project/reset_confirm.html'
    success_url = 'login/'
    form_valid_message = 'Your password was changed!'

    def form_valid(self, form):
        form.save()
        return super().form_valid(form)


@login_required
def order_add(request):
    if request.method == 'POST':
        content = request.POST['content']
        order = Order.objects.create(user=User.objects.get(username=request.user.username), content=content)
        order.save()
        return redirect('/order/list')
    return render(request, 'project/order/add_order.html')


class OrderListView(generic.ListView):
    model = Order
    template_name = 'project/order/order_list.html'

    def get_queryset(self):
        return Order.objects.filter(user=User.objects.get(username=self.request.user.username))
