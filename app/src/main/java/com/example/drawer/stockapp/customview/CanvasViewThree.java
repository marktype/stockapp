package com.example.drawer.stockapp.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2016/6/20.
 */
public class CanvasViewThree extends View {
    private Paint paint;
    private RectF rectF;
    private static final float DEFAULT_RADIUS = 100;
    private float radius = DEFAULT_RADIUS;// 默认圆环半径为300px
    private List<HashMap<String,Object>> data;//圆环的颜色数据

    private static final int DEFAULT_COLOR =  Color.parseColor("#D3D3D3");
    private int defaultColor = DEFAULT_COLOR;//设置默认颜色，即即使数据没有占到100%，那么使用默认颜色构造圆环

    private static final float DEFAULT_STROK_WIDTH = 5;
    private float strokeWidth = DEFAULT_STROK_WIDTH;

    //数据map中的键  map.put(CanvasView.TITLE,"例如")
    public static final String TITLE = "title";//title
    public static final String COLOR = "color";//color
    public static final String WEIGHT = "weight";//权重


    public CanvasViewThree(Context context) {
        super(context);
        init();
    }

    public CanvasViewThree(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CanvasViewThree(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);     //画笔的风格
        paint.setStrokeWidth(strokeWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /**将圆环画到最中央*/
        float width = getWidth();
        float height = getHeight();
        rectF = new RectF(width/2 - radius,height/2 - radius,width/2 + radius,height/2 + radius);

        //得到圆环内切正方形的宽度，便于用来绘制文字
        float rectWidth = (float) Math.sqrt(2*(radius - 100)*(radius - 100));

        //要绘制文字的起始x坐标
        float txtX = width/2 - rectWidth/2;

        /**
         * 先用默认颜色画一个完整的圆环,以后的绘制都是直接覆盖在这个圆环上面
         */
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(defaultColor);
//        paint.setTextSize(30);
        canvas.drawArc(rectF,0,360,false,paint);

        /**
         * 如果data为null则不绘制
         */
        if(data == null || data.size() == 0){
            return;
        }

        float totalWeight = 0;//记录总共绘制了多少权重
        float startAngle = 0;
        int size = data.size();
        float sigleRectHeight = rectWidth/size;
        float cy = 0;
        try {
            for (int i = 0;i<size;i++){
                Map<String,Object> map = data.get(i);
                String title = (String) map.get(CanvasViewThree.TITLE);
                int color = (int) map.get(CanvasViewThree.COLOR);
                float weight = (float) map.get(CanvasViewThree.WEIGHT);

                totalWeight += weight;//计算总弧度，便于抛异常
                float sweepAngle = weight*360;

                /**
                 * 画圆环
                 */
                paint.setStrokeWidth(strokeWidth);
                paint.setColor(color);
                canvas.drawArc(rectF,startAngle,sweepAngle,false,paint);

                /**
                 * 画百分比
                 */
//                float angel = startAngle + sweepAngle/2;
                //计算要画百分比文字的位置
//                float x = (float) (width/2 + (radius)*Math.cos(convertAngleToPi(angel)));
//                float y = (float) (height/2 + (radius)*Math.sin(convertAngleToPi(angel)));
//                paint.setColor(Color.WHITE);
//                paint.setTextAlign(Paint.Align.CENTER);//设置TextAlign为center
//                paint.setStrokeWidth(1);
//                canvas.drawText(dealWithFloat(weight),x,y,paint);

                startAngle += sweepAngle;

//                /**
//                 *  画内部小圆圈
//                 */
//                paint.setColor(color);
//                paint.setStrokeWidth(14);
//                //计算小圆点中心的y坐标
//                cy =  rectWidth/2 + height/2 - ((size -i)*sigleRectHeight) + sigleRectHeight/2 ;
//                canvas.drawCircle(txtX+30,cy,8,paint);

//                /**
//                 * 画文字
//                 */
//                paint.setTextAlign(Paint.Align.CENTER);
//                paint.setColor(Color.BLACK);
//                paint.setStrokeWidth(1);
//                paint.setTextSize(40f);
//                //已知中线计算基线位置
//                Paint.FontMetrics metrics = paint.getFontMetrics();
//                float baseLine  = cy - (metrics.bottom + metrics.top)/2;
//                canvas.drawText(title,txtX,baseLine,paint);
            }



            if(totalWeight > 1){
                throw new IllegalArgumentException("the total weight of your data must be less than or equal to 1 !!!屌毛，你的圆弧总比例必须小于等于1才要得，不然老子要给你抛异常");
            }
        }catch (ClassCastException e){
            e.printStackTrace();
            throw new IllegalArgumentException("the map of you data,has some illegalArgumentException !!!   屌毛，你的map在存放数据的时候一定要注意数据类型，尤其是weight是float型的，必须加个f，不然老子又有给你抛异常");
        }



    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int withMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int width = 0;
        if(withMode == MeasureSpec.EXACTLY){
            width = widthSize;
        }else {
            width = (int) (2*(radius + strokeWidth));
        }

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int height = 0;
        if(heightMode == MeasureSpec.EXACTLY){
            height = heightSize;
        }else {
            height = (int) (2*(radius + strokeWidth));
        }
        setMeasuredDimension(width,height);
    }

    /**
     * 设置数据源
     * @param data  该参数为设置圆环的数据，其中list的一个index所对应的map中就包含了颜色，所占百分比，title三项数据
     */
    public void setData(@Nullable ArrayList<HashMap<String,Object>> data){
        this.data = data;
        invalidate();
    }

    /**
     *  设置圆环半径
     * @param radius
     */
    public void setRadius( float radius){
        this.radius = radius;
        invalidate();
    }

    /**
     * 设置默认颜色
     * @param color  数据没有占满圆环，那么用默认颜色构成一个完整的圆环
     */
    public void setDefaultColor(int color){
        this.defaultColor = color;
        invalidate();
    }

    /**
     * 设置圆环的宽度
     * @param width
     */
    public void setStrokeWidth(float width){
        this.strokeWidth = width;
        invalidate();
    }



    /**
     * 将角度，转换成弧度
     * @param angle
     * @return
     */
    private float convertAngleToPi(float angle){
        return (float) ((angle/180)*Math.PI);
    }

    /**
     * 截除右边为0的数
     * @param value
     * @return
     */
    private String dealWithFloat(float value){
        int temp = (int) (value*100);

        return temp + "%";
    }
}
