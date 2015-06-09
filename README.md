# reside-menu
An ResideMenu implements by using sliding menu

和原来库的不同之处在于
```Java
sm.setAboveCanvasTransformer(new SlidingMenu.CanvasTransformer() {
            @Override
            public void transformCanvas(Canvas canvas, float percentOpen,float scrollX) {
                float scale = (float) (1 - percentOpen * 0.25);
                if(scrollX<0){
                    canvas.scale(scale, scale, 0, canvas.getHeight() / 2);
                }else{
                    canvas.scale(scale, scale, canvas.getWidth(), canvas.getHeight() / 2);
                }
            }
        });
        
```Java
SlidingMenu.CanvasTransformer中的transformCanvas增加了scrollX参数，
可用来判断当前是右滑菜单还是左划菜单做出相应处理，详见Demo
