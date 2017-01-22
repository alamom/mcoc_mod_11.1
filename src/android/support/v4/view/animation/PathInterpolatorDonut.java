package android.support.v4.view.animation;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.view.animation.Interpolator;

class PathInterpolatorDonut
  implements Interpolator
{
  private static final float PRECISION = 0.002F;
  private final float[] mX;
  private final float[] mY;
  
  public PathInterpolatorDonut(float paramFloat1, float paramFloat2)
  {
    this(createQuad(paramFloat1, paramFloat2));
  }
  
  public PathInterpolatorDonut(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    this(createCubic(paramFloat1, paramFloat2, paramFloat3, paramFloat4));
  }
  
  public PathInterpolatorDonut(Path paramPath)
  {
    PathMeasure localPathMeasure = new PathMeasure(paramPath, false);
    float f = localPathMeasure.getLength();
    int j = (int)(f / 0.002F) + 1;
    this.mX = new float[j];
    this.mY = new float[j];
    paramPath = new float[2];
    for (int i = 0; i < j; i++)
    {
      localPathMeasure.getPosTan(i * f / (j - 1), paramPath, null);
      this.mX[i] = paramPath[0];
      this.mY[i] = paramPath[1];
    }
  }
  
  private static Path createCubic(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    Path localPath = new Path();
    localPath.moveTo(0.0F, 0.0F);
    localPath.cubicTo(paramFloat1, paramFloat2, paramFloat3, paramFloat4, 1.0F, 1.0F);
    return localPath;
  }
  
  private static Path createQuad(float paramFloat1, float paramFloat2)
  {
    Path localPath = new Path();
    localPath.moveTo(0.0F, 0.0F);
    localPath.quadTo(paramFloat1, paramFloat2, 1.0F, 1.0F);
    return localPath;
  }
  
  public float getInterpolation(float paramFloat)
  {
    float f = 0.0F;
    if (paramFloat <= 0.0F) {
      paramFloat = f;
    }
    for (;;)
    {
      return paramFloat;
      if (paramFloat >= 1.0F)
      {
        paramFloat = 1.0F;
      }
      else
      {
        int i = 0;
        int j = this.mX.length - 1;
        while (j - i > 1)
        {
          int k = (i + j) / 2;
          if (paramFloat < this.mX[k]) {
            j = k;
          } else {
            i = k;
          }
        }
        f = this.mX[j] - this.mX[i];
        if (f == 0.0F)
        {
          paramFloat = this.mY[i];
        }
        else
        {
          paramFloat = (paramFloat - this.mX[i]) / f;
          f = this.mY[i];
          paramFloat = (this.mY[j] - f) * paramFloat + f;
        }
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\android\support\v4\view\animation\PathInterpolatorDonut.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */