clear
clc
% learning part
load train79.mat
x = d79;
% build y vector
y = zeros(2000,1);
for n = 1:1:2000
   if n <= 1000
       y(n,1) = -1; % 7 class is -1
   else
       y(n,1) = 1; % 9 class is 1
   end
end
Ensemble = fitensemble(x,y,'AdaBoostM1',150,'Tree');
load train79.mat
x_test = d79;
result = predict(Ensemble,x_test);
error_count = 0;
for n =1:1:2000
   y_predict = result(n,1);
   if n<=1000
      if y_predict ~= -1
          error_count = error_count + 1;
      end
   else
      if y_predict ~= 1
          error_count = error_count + 1;
      end
   end
end
rate = (2000-error_count)/2000;
disp(rate)