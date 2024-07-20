clear, close all

img = imread('lena_g.bmp');
[tmp, map] = imread('lena_g.bmp');
fft_img = fftshift(fft2(double(img)));

[M, N] = size(fft_img);
filter_sizes = [10, 50, 100];

figure;

% 元画像を表示
subplot(2, 2, 1);
imagesc(img);
colormap('gray');
colorbar;
title('Original Image');

for i = 1:length(filter_sizes)
    w = filter_sizes(i);
    
    [X, Y] = meshgrid(1:N, 1:M);
    center_x = ceil(N/2);
    center_y = ceil(M/2);

    H = ((X - center_x).^2 + (Y - center_y).^2) > (w / 2)^2;

    filtered_fft_img = fft_img .* H;

    filtered_img = ifft2(ifftshift(filtered_fft_img));
    filtered_img = real(filtered_img);
    filtered_img(filtered_img < 0) = 0;

    subplot(2, 2, i+1);
    imagesc(filtered_img);
    colorbar;
    title(['Filtered Image with w = ', num2str(w)]);
end