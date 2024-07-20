clear, close all

noise01 = imread('noise01.bmp');
noise02 = imread('noise02.bmp');

median_filtered_noise01 = medfilt2(noise01, [3 3]);
median_filtered_noise02 = medfilt2(noise02, [3 3]);

figure;
subplot(2, 2, 1), imshow(noise01), title('noise01.bmp');
subplot(2, 2, 2), imshow(noise02), title('noise02.bmp');
subplot(2, 2, 3), imshow(median_filtered_noise01), title('Median Filtered Noise 01');
subplot(2, 2, 4), imshow(median_filtered_noise02), title('Median Filtered Noise 02');