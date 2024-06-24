clear, close all
f = 100;
t = linspace(0, 0.1, 1024);

x = 4 * sin(2 * pi * f * t);

noise = randn(1, 1024);
x_noised = x + noise;

figure;
subplot(3, 1, 1);
plot(t, x, ".");
title('Original Signal');
xlabel('t');
ylabel('Amplitude');
grid on;

subplot(3, 1, 2);
plot(t, x_noised, ".");
title('Noised Signal');
xlabel('t');
ylabel('Amplitude');
grid on;

subplot(3, 1, 3);
plot(t, noise, ".");
title('Added Noise');
xlabel('t');
ylabel('Amplitude');
grid on;

sgtitle('Signals');
