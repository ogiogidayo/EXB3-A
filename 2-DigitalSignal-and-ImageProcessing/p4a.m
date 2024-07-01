clear, close all
function y = custom_sinc(x)
    y = ones(size(x));
    idx = x ~= 0;
    y(idx) = sin(pi*x(idx))./(pi*x(idx));
end

tau1 = 0;
T1 = 1;
tau2 = 2;
T2 = 0.8;
sampling_period = 0.1;
t = -4:sampling_period:4;

x1 = custom_sinc((t - tau1) / T1);
x2 = custom_sinc((t - tau2) / T2);

figure;
subplot(2,1,1);
scatter(t, x1, 10, 'filled');
title('x = sinc((t - \tau) / T) with \tau = 0, T = 1');
xlabel('t');
ylabel('x');
grid on;

subplot(2,1,2);
scatter(t, x2, 10, 'filled');
title('x = sinc((t - \tau) / T) with \tau = 2, T = 0.8');
xlabel('t');
ylabel('x');
grid on;