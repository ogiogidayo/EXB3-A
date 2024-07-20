clear, close all

img = imread('lena_g.bmp');

img = double(img);

fftimg = fft2(img);

fftimg_shifted = fftshift(fftimg);

pwrimg = fftimg_shifted .* conj(fftimg_shifted);

log_pwrimg = log(1 + pwrimg);

figure;
imagesc(log_pwrimg);
colormap('gray');
colorbar;
title('Logarithmic Power Spectrum of the DFT Coefficients');
axis image;
