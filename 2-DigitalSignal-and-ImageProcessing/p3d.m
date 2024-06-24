clear, close all
f = 100;
fs = 1024;
t = linspace(0, 0.5, fs);

x = 4 * sin(2 * pi * f * t);

noise = randn(1, fs);
x_noised = x + noise;

X_noised = fft(x_noised);

window = zeros(1, fs);
window(44:55) = 1;
window(970:980) = 1;

X_filtered = X_noised .* window;

x_filtered = real(ifft(X_filtered));

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
xlabel('t');
ylabel('Amplitude');
grid on;

subplot(3, 1, 3);
plot(t, x_filtered);
title('Filtered Signal');
xlabel('t');
ylabel('Amplitude');
grid on;

% Adjust layout
sgtitle('Original Signal, Noised Signal, and Filtered Signal');
