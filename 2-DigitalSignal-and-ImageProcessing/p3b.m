clear, close all
f = 100;
fs = 1024;
t = linspace(0, 0.5, fs);

x = 4 * sin(2 * pi * f * t);

noise = randn(1, fs);
x_noised = x + noise;

figure;
subplot(3, 1, 1);
plot(t, x);
title('Original Signal');
xlabel('t');
ylabel('Amplitude');
grid on;

subplot(3, 1, 2);
plot(t, x_noised);
title('Noised Signal');
xlabel('Time (s)');
ylabel('Amplitude');
grid on;

subplot(3, 1, 3);
plot(t, noise);
title('Added Noise');
xlabel('Time (s)');
ylabel('Amplitude');
grid on;

sgtitle('Original Signal, Noised Signal, and Added Noise');
