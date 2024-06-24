clear, close all
x = -10:0.1:10;

a = 0;

sigma1 = 1;
sigma2 = 2;
sigma3 = 3;

p1 = (1/(sigma1 * sqrt(2*pi))) * exp(-((x-a).^2) / (2*sigma1^2));
p2 = (1/(sigma2 * sqrt(2*pi))) * exp(-((x-a).^2) / (2*sigma2^2));
p3 = (1/(sigma3 * sqrt(2*pi))) * exp(-((x-a).^2) / (2*sigma3^2));

figure;
hold on;
grid on
plot(x, p1, 'r', 'DisplayName', '\sigma = 1');
plot(x, p2, 'g', 'DisplayName', '\sigma = 2');
plot(x, p3, 'b', 'DisplayName', '\sigma = 3');
legend show;
title('Gaussian Functions');
xlabel('x');
ylabel('p(x)');
hold off;
