clear, close all
f = 100;
t = linspace(0, 0.1, 1024);

x = 4 * sin(2 * pi * f * t);

X = fft(x);

x_inv = real(ifft(X));

figure;
subplot(2, 1, 1);
plot(t, x, ".");
title('Original Signal');
xlabel('t');
ylabel('Amplitude');
grid on;

subplot(2, 1, 2);
plot(t, x_inv, ".");
title('Inverse FFT');
xlabel('t');
ylabel('Amplitude');
grid on;

sgtitle('Signal');
