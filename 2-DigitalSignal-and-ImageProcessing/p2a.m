clear, close all
t = linspace(0, 4 * pi, 256);
sigmas = [-0.1, 0.0, 0.1];

figure;

for i = 1:3
    sigma = sigmas(i);
    y = exp(sigma * t) .* cos(t);
    
    subplot(3, 1, i);
    plot(t/pi, y);
    xlabel('t');
    ylabel(['y (\sigma = ', num2str(sigma), ')']);
    grid on;
end

sgtitle('y = e^{\sigma} cos(t)');