clear, close all
f = 100;
fs = 1024;
t = linspace(0, 0.1, fs);
x = 4 * sin(2 * pi * f * t);

noise = randn(1, fs);

x_noised = x + noise;

X = fft(x);
X_noised = fft(x_noised);
Noise = fft(noise);

Amp_X = abs(X);
Amp_X_noised = abs(X_noised);
Amp_Noise = abs(Noise);

freq = (0:fs-1) * (fs / (2 * pi * fs));

figure;

subplot(3, 1, 1);
plot(freq, Amp_X, ".");
title('DFT of Original Signal');
xlabel('f (Hz)');
ylabel('Amplitude');
xlim([0 165]);
ylim([0 3000]);
grid on;

subplot(3, 1, 2);
plot(freq, Amp_Noise, ".");
title('DFT of Added Noise');
xlabel('f (Hz)');
ylabel('Amplitude');
xlim([0 165]);
grid on;

subplot(3, 1, 3);
plot(freq, Amp_X_noised, ".");
title('DFT of Noised Signal');
xlabel('f (Hz)');
ylabel('Amplitude');
xlim([0 165]);
ylim([0 3000]);
grid on;

sgtitle('DFT');
