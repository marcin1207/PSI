
P = [rand(1,500)*2; rand(1,500)];
net = newsom([0 2; 0 1],[5 6]);
plotsom(net.layers{1}.positions)
net.trainParam.epochs =20;
net = train(net,P);
plot(P(1,:),P(2,:),'.g','markersize',10)
hold on
plotsom(net.iw{1,1},net.layers{1}.distances)
hold off