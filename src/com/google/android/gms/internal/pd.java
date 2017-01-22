package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

public final class pd
  extends ph<pd>
{
  public a[] awd;
  
  public pd()
  {
    qc();
  }
  
  public static pd n(byte[] paramArrayOfByte)
    throws pm
  {
    return (pd)pn.a(new pd(), paramArrayOfByte);
  }
  
  public void a(pg parampg)
    throws IOException
  {
    if ((this.awd != null) && (this.awd.length > 0)) {
      for (int i = 0; i < this.awd.length; i++)
      {
        a locala = this.awd[i];
        if (locala != null) {
          parampg.a(1, locala);
        }
      }
    }
    super.a(parampg);
  }
  
  protected int c()
  {
    int i = super.c();
    int k = i;
    if (this.awd != null)
    {
      k = i;
      if (this.awd.length > 0)
      {
        int j = 0;
        for (;;)
        {
          k = i;
          if (j >= this.awd.length) {
            break;
          }
          a locala = this.awd[j];
          k = i;
          if (locala != null) {
            k = i + pg.c(1, locala);
          }
          j++;
          i = k;
        }
      }
    }
    return k;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1;
    if (paramObject == this) {
      bool1 = true;
    }
    for (;;)
    {
      return bool1;
      bool1 = bool2;
      if ((paramObject instanceof pd))
      {
        paramObject = (pd)paramObject;
        bool1 = bool2;
        if (pl.equals(this.awd, ((pd)paramObject).awd)) {
          bool1 = a((ph)paramObject);
        }
      }
    }
  }
  
  public int hashCode()
  {
    return (pl.hashCode(this.awd) + 527) * 31 + qz();
  }
  
  public pd q(pf parampf)
    throws IOException
  {
    for (;;)
    {
      int i = parampf.qi();
      switch (i)
      {
      default: 
        if (a(parampf, i)) {}
        break;
      case 0: 
        return this;
      case 10: 
        int j = pq.b(parampf, 10);
        if (this.awd == null) {}
        a[] arrayOfa;
        for (i = 0;; i = this.awd.length)
        {
          arrayOfa = new a[j + i];
          j = i;
          if (i != 0) {
            System.arraycopy(this.awd, 0, arrayOfa, 0, i);
          }
          for (j = i; j < arrayOfa.length - 1; j++)
          {
            arrayOfa[j] = new a();
            parampf.a(arrayOfa[j]);
            parampf.qi();
          }
        }
        arrayOfa[j] = new a();
        parampf.a(arrayOfa[j]);
        this.awd = arrayOfa;
      }
    }
  }
  
  public pd qc()
  {
    this.awd = a.qd();
    this.awJ = null;
    this.awU = -1;
    return this;
  }
  
  public static final class a
    extends ph<a>
  {
    private static volatile a[] awe;
    public a awf;
    public String name;
    
    public a()
    {
      qe();
    }
    
    public static a[] qd()
    {
      if (awe == null) {}
      synchronized (pl.awT)
      {
        if (awe == null) {
          awe = new a[0];
        }
        return awe;
      }
    }
    
    public void a(pg parampg)
      throws IOException
    {
      parampg.b(1, this.name);
      if (this.awf != null) {
        parampg.a(2, this.awf);
      }
      super.a(parampg);
    }
    
    protected int c()
    {
      int j = super.c() + pg.j(1, this.name);
      int i = j;
      if (this.awf != null) {
        i = j + pg.c(2, this.awf);
      }
      return i;
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = false;
      boolean bool1;
      if (paramObject == this) {
        bool1 = true;
      }
      for (;;)
      {
        return bool1;
        bool1 = bool2;
        if ((paramObject instanceof a))
        {
          paramObject = (a)paramObject;
          if (this.name == null)
          {
            bool1 = bool2;
            if (((a)paramObject).name != null) {
              continue;
            }
            label41:
            if (this.awf != null) {
              break label85;
            }
            bool1 = bool2;
            if (((a)paramObject).awf != null) {
              continue;
            }
          }
          label85:
          while (this.awf.equals(((a)paramObject).awf))
          {
            bool1 = a((ph)paramObject);
            break;
            if (this.name.equals(((a)paramObject).name)) {
              break label41;
            }
            bool1 = bool2;
            break;
          }
          bool1 = bool2;
        }
      }
    }
    
    public int hashCode()
    {
      int j = 0;
      int i;
      if (this.name == null)
      {
        i = 0;
        if (this.awf != null) {
          break label48;
        }
      }
      for (;;)
      {
        return ((i + 527) * 31 + j) * 31 + qz();
        i = this.name.hashCode();
        break;
        label48:
        j = this.awf.hashCode();
      }
    }
    
    public a qe()
    {
      this.name = "";
      this.awf = null;
      this.awJ = null;
      this.awU = -1;
      return this;
    }
    
    public a r(pf parampf)
      throws IOException
    {
      for (;;)
      {
        int i = parampf.qi();
        switch (i)
        {
        default: 
          if (a(parampf, i)) {}
          break;
        case 0: 
          return this;
        case 10: 
          this.name = parampf.readString();
          break;
        case 18: 
          if (this.awf == null) {
            this.awf = new a();
          }
          parampf.a(this.awf);
        }
      }
    }
    
    public static final class a
      extends ph<a>
    {
      private static volatile a[] awg;
      public a awh;
      public int type;
      
      public a()
      {
        qg();
      }
      
      public static a[] qf()
      {
        if (awg == null) {}
        synchronized (pl.awT)
        {
          if (awg == null) {
            awg = new a[0];
          }
          return awg;
        }
      }
      
      public void a(pg parampg)
        throws IOException
      {
        parampg.s(1, this.type);
        if (this.awh != null) {
          parampg.a(2, this.awh);
        }
        super.a(parampg);
      }
      
      protected int c()
      {
        int j = super.c() + pg.u(1, this.type);
        int i = j;
        if (this.awh != null) {
          i = j + pg.c(2, this.awh);
        }
        return i;
      }
      
      public boolean equals(Object paramObject)
      {
        boolean bool2 = false;
        boolean bool1;
        if (paramObject == this) {
          bool1 = true;
        }
        for (;;)
        {
          return bool1;
          bool1 = bool2;
          if ((paramObject instanceof a))
          {
            paramObject = (a)paramObject;
            bool1 = bool2;
            if (this.type == ((a)paramObject).type) {
              if (this.awh == null)
              {
                bool1 = bool2;
                if (((a)paramObject).awh != null) {}
              }
              else
              {
                while (this.awh.equals(((a)paramObject).awh))
                {
                  bool1 = a((ph)paramObject);
                  break;
                }
                bool1 = bool2;
              }
            }
          }
        }
      }
      
      public int hashCode()
      {
        int j = this.type;
        if (this.awh == null) {}
        for (int i = 0;; i = this.awh.hashCode()) {
          return (i + (j + 527) * 31) * 31 + qz();
        }
      }
      
      public a qg()
      {
        this.type = 1;
        this.awh = null;
        this.awJ = null;
        this.awU = -1;
        return this;
      }
      
      public a s(pf parampf)
        throws IOException
      {
        for (;;)
        {
          int i = parampf.qi();
          switch (i)
          {
          default: 
            if (a(parampf, i)) {}
            break;
          case 0: 
            return this;
          case 8: 
            i = parampf.ql();
            switch (i)
            {
            default: 
              break;
            case 1: 
            case 2: 
            case 3: 
            case 4: 
            case 5: 
            case 6: 
            case 7: 
            case 8: 
            case 9: 
            case 10: 
            case 11: 
            case 12: 
            case 13: 
            case 14: 
            case 15: 
              this.type = i;
            }
            break;
          case 18: 
            if (this.awh == null) {
              this.awh = new a();
            }
            parampf.a(this.awh);
          }
        }
      }
      
      public static final class a
        extends ph<a>
      {
        public byte[] awi;
        public String awj;
        public double awk;
        public float awl;
        public long awm;
        public int awn;
        public int awo;
        public boolean awp;
        public pd.a[] awq;
        public pd.a.a[] awr;
        public String[] aws;
        public long[] awt;
        public float[] awu;
        public long awv;
        
        public a()
        {
          qh();
        }
        
        public void a(pg parampg)
          throws IOException
        {
          int j = 0;
          if (!Arrays.equals(this.awi, pq.axd)) {
            parampg.a(1, this.awi);
          }
          if (!this.awj.equals("")) {
            parampg.b(2, this.awj);
          }
          if (Double.doubleToLongBits(this.awk) != Double.doubleToLongBits(0.0D)) {
            parampg.a(3, this.awk);
          }
          if (Float.floatToIntBits(this.awl) != Float.floatToIntBits(0.0F)) {
            parampg.b(4, this.awl);
          }
          if (this.awm != 0L) {
            parampg.b(5, this.awm);
          }
          if (this.awn != 0) {
            parampg.s(6, this.awn);
          }
          if (this.awo != 0) {
            parampg.t(7, this.awo);
          }
          if (this.awp) {
            parampg.b(8, this.awp);
          }
          int i;
          Object localObject;
          if ((this.awq != null) && (this.awq.length > 0)) {
            for (i = 0; i < this.awq.length; i++)
            {
              localObject = this.awq[i];
              if (localObject != null) {
                parampg.a(9, (pn)localObject);
              }
            }
          }
          if ((this.awr != null) && (this.awr.length > 0)) {
            for (i = 0; i < this.awr.length; i++)
            {
              localObject = this.awr[i];
              if (localObject != null) {
                parampg.a(10, (pn)localObject);
              }
            }
          }
          if ((this.aws != null) && (this.aws.length > 0)) {
            for (i = 0; i < this.aws.length; i++)
            {
              localObject = this.aws[i];
              if (localObject != null) {
                parampg.b(11, (String)localObject);
              }
            }
          }
          if ((this.awt != null) && (this.awt.length > 0)) {
            for (i = 0; i < this.awt.length; i++) {
              parampg.b(12, this.awt[i]);
            }
          }
          if (this.awv != 0L) {
            parampg.b(13, this.awv);
          }
          if ((this.awu != null) && (this.awu.length > 0)) {
            for (i = j; i < this.awu.length; i++) {
              parampg.b(14, this.awu[i]);
            }
          }
          super.a(parampg);
        }
        
        protected int c()
        {
          int i2 = 0;
          int j = super.c();
          int i = j;
          if (!Arrays.equals(this.awi, pq.axd)) {
            i = j + pg.b(1, this.awi);
          }
          j = i;
          if (!this.awj.equals("")) {
            j = i + pg.j(2, this.awj);
          }
          i = j;
          if (Double.doubleToLongBits(this.awk) != Double.doubleToLongBits(0.0D)) {
            i = j + pg.b(3, this.awk);
          }
          j = i;
          if (Float.floatToIntBits(this.awl) != Float.floatToIntBits(0.0F)) {
            j = i + pg.c(4, this.awl);
          }
          int k = j;
          if (this.awm != 0L) {
            k = j + pg.d(5, this.awm);
          }
          i = k;
          if (this.awn != 0) {
            i = k + pg.u(6, this.awn);
          }
          j = i;
          if (this.awo != 0) {
            j = i + pg.v(7, this.awo);
          }
          i = j;
          if (this.awp) {
            i = j + pg.c(8, this.awp);
          }
          j = i;
          Object localObject;
          if (this.awq != null)
          {
            j = i;
            if (this.awq.length > 0)
            {
              k = 0;
              while (k < this.awq.length)
              {
                localObject = this.awq[k];
                j = i;
                if (localObject != null) {
                  j = i + pg.c(9, (pn)localObject);
                }
                k++;
                i = j;
              }
              j = i;
            }
          }
          i = j;
          if (this.awr != null)
          {
            i = j;
            if (this.awr.length > 0)
            {
              i = j;
              k = 0;
              while (k < this.awr.length)
              {
                localObject = this.awr[k];
                j = i;
                if (localObject != null) {
                  j = i + pg.c(10, (pn)localObject);
                }
                k++;
                i = j;
              }
            }
          }
          j = i;
          if (this.aws != null)
          {
            j = i;
            if (this.aws.length > 0)
            {
              k = 0;
              int n = 0;
              int m;
              for (j = 0; k < this.aws.length; j = m)
              {
                localObject = this.aws[k];
                int i1 = n;
                m = j;
                if (localObject != null)
                {
                  m = j + 1;
                  i1 = n + pg.di((String)localObject);
                }
                k++;
                n = i1;
              }
              j = i + n + j * 1;
            }
          }
          i = j;
          if (this.awt != null)
          {
            i = j;
            if (this.awt.length > 0)
            {
              i = 0;
              for (k = i2; k < this.awt.length; k++) {
                i += pg.D(this.awt[k]);
              }
              i = j + i + this.awt.length * 1;
            }
          }
          j = i;
          if (this.awv != 0L) {
            j = i + pg.d(13, this.awv);
          }
          i = j;
          if (this.awu != null)
          {
            i = j;
            if (this.awu.length > 0) {
              i = j + this.awu.length * 4 + this.awu.length * 1;
            }
          }
          return i;
        }
        
        public boolean equals(Object paramObject)
        {
          boolean bool2 = false;
          boolean bool1;
          if (paramObject == this) {
            bool1 = true;
          }
          for (;;)
          {
            return bool1;
            bool1 = bool2;
            if ((paramObject instanceof a))
            {
              paramObject = (a)paramObject;
              bool1 = bool2;
              if (Arrays.equals(this.awi, ((a)paramObject).awi)) {
                if (this.awj == null)
                {
                  bool1 = bool2;
                  if (((a)paramObject).awj != null) {}
                }
                else
                {
                  while (this.awj.equals(((a)paramObject).awj))
                  {
                    bool1 = bool2;
                    if (Double.doubleToLongBits(this.awk) != Double.doubleToLongBits(((a)paramObject).awk)) {
                      break;
                    }
                    bool1 = bool2;
                    if (Float.floatToIntBits(this.awl) != Float.floatToIntBits(((a)paramObject).awl)) {
                      break;
                    }
                    bool1 = bool2;
                    if (this.awm != ((a)paramObject).awm) {
                      break;
                    }
                    bool1 = bool2;
                    if (this.awn != ((a)paramObject).awn) {
                      break;
                    }
                    bool1 = bool2;
                    if (this.awo != ((a)paramObject).awo) {
                      break;
                    }
                    bool1 = bool2;
                    if (this.awp != ((a)paramObject).awp) {
                      break;
                    }
                    bool1 = bool2;
                    if (!pl.equals(this.awq, ((a)paramObject).awq)) {
                      break;
                    }
                    bool1 = bool2;
                    if (!pl.equals(this.awr, ((a)paramObject).awr)) {
                      break;
                    }
                    bool1 = bool2;
                    if (!pl.equals(this.aws, ((a)paramObject).aws)) {
                      break;
                    }
                    bool1 = bool2;
                    if (!pl.equals(this.awt, ((a)paramObject).awt)) {
                      break;
                    }
                    bool1 = bool2;
                    if (!pl.equals(this.awu, ((a)paramObject).awu)) {
                      break;
                    }
                    bool1 = bool2;
                    if (this.awv != ((a)paramObject).awv) {
                      break;
                    }
                    bool1 = a((ph)paramObject);
                    break;
                  }
                  bool1 = bool2;
                }
              }
            }
          }
        }
        
        public int hashCode()
        {
          int k = Arrays.hashCode(this.awi);
          int i;
          int i3;
          int n;
          int m;
          int i1;
          int i2;
          if (this.awj == null)
          {
            i = 0;
            long l = Double.doubleToLongBits(this.awk);
            i3 = (int)(l ^ l >>> 32);
            n = Float.floatToIntBits(this.awl);
            m = (int)(this.awm ^ this.awm >>> 32);
            i1 = this.awn;
            i2 = this.awo;
            if (!this.awp) {
              break label221;
            }
          }
          label221:
          for (int j = 1231;; j = 1237)
          {
            return (((((((j + ((((((i + (k + 527) * 31) * 31 + i3) * 31 + n) * 31 + m) * 31 + i1) * 31 + i2) * 31) * 31 + pl.hashCode(this.awq)) * 31 + pl.hashCode(this.awr)) * 31 + pl.hashCode(this.aws)) * 31 + pl.hashCode(this.awt)) * 31 + pl.hashCode(this.awu)) * 31 + (int)(this.awv ^ this.awv >>> 32)) * 31 + qz();
            i = this.awj.hashCode();
            break;
          }
        }
        
        public a qh()
        {
          this.awi = pq.axd;
          this.awj = "";
          this.awk = 0.0D;
          this.awl = 0.0F;
          this.awm = 0L;
          this.awn = 0;
          this.awo = 0;
          this.awp = false;
          this.awq = pd.a.qd();
          this.awr = pd.a.a.qf();
          this.aws = pq.axb;
          this.awt = pq.awX;
          this.awu = pq.awY;
          this.awv = 0L;
          this.awJ = null;
          this.awU = -1;
          return this;
        }
        
        public a t(pf parampf)
          throws IOException
        {
          for (;;)
          {
            int i = parampf.qi();
            int j;
            Object localObject;
            int k;
            switch (i)
            {
            default: 
              if (a(parampf, i)) {}
              break;
            case 0: 
              return this;
            case 10: 
              this.awi = parampf.readBytes();
              break;
            case 18: 
              this.awj = parampf.readString();
              break;
            case 25: 
              this.awk = parampf.readDouble();
              break;
            case 37: 
              this.awl = parampf.readFloat();
              break;
            case 40: 
              this.awm = parampf.qk();
              break;
            case 48: 
              this.awn = parampf.ql();
              break;
            case 56: 
              this.awo = parampf.qn();
              break;
            case 64: 
              this.awp = parampf.qm();
              break;
            case 74: 
              j = pq.b(parampf, 74);
              if (this.awq == null) {}
              for (i = 0;; i = this.awq.length)
              {
                localObject = new pd.a[j + i];
                j = i;
                if (i != 0) {
                  System.arraycopy(this.awq, 0, localObject, 0, i);
                }
                for (j = i; j < localObject.length - 1; j++)
                {
                  localObject[j] = new pd.a();
                  parampf.a(localObject[j]);
                  parampf.qi();
                }
              }
              localObject[j] = new pd.a();
              parampf.a(localObject[j]);
              this.awq = ((pd.a[])localObject);
              break;
            case 82: 
              j = pq.b(parampf, 82);
              if (this.awr == null) {}
              for (i = 0;; i = this.awr.length)
              {
                localObject = new pd.a.a[j + i];
                j = i;
                if (i != 0) {
                  System.arraycopy(this.awr, 0, localObject, 0, i);
                }
                for (j = i; j < localObject.length - 1; j++)
                {
                  localObject[j] = new pd.a.a();
                  parampf.a(localObject[j]);
                  parampf.qi();
                }
              }
              localObject[j] = new pd.a.a();
              parampf.a(localObject[j]);
              this.awr = ((pd.a.a[])localObject);
              break;
            case 90: 
              j = pq.b(parampf, 90);
              if (this.aws == null) {}
              for (i = 0;; i = this.aws.length)
              {
                localObject = new String[j + i];
                j = i;
                if (i != 0) {
                  System.arraycopy(this.aws, 0, localObject, 0, i);
                }
                for (j = i; j < localObject.length - 1; j++)
                {
                  localObject[j] = parampf.readString();
                  parampf.qi();
                }
              }
              localObject[j] = parampf.readString();
              this.aws = ((String[])localObject);
              break;
            case 96: 
              j = pq.b(parampf, 96);
              if (this.awt == null) {}
              for (i = 0;; i = this.awt.length)
              {
                localObject = new long[j + i];
                j = i;
                if (i != 0) {
                  System.arraycopy(this.awt, 0, localObject, 0, i);
                }
                for (j = i; j < localObject.length - 1; j++)
                {
                  localObject[j] = parampf.qk();
                  parampf.qi();
                }
              }
              localObject[j] = parampf.qk();
              this.awt = ((long[])localObject);
              break;
            case 98: 
              k = parampf.gp(parampf.qp());
              i = parampf.getPosition();
              for (j = 0; parampf.qu() > 0; j++) {
                parampf.qk();
              }
              parampf.gr(i);
              if (this.awt == null) {}
              for (i = 0;; i = this.awt.length)
              {
                localObject = new long[j + i];
                j = i;
                if (i != 0) {
                  System.arraycopy(this.awt, 0, localObject, 0, i);
                }
                for (j = i; j < localObject.length; j++) {
                  localObject[j] = parampf.qk();
                }
              }
              this.awt = ((long[])localObject);
              parampf.gq(k);
              break;
            case 104: 
              this.awv = parampf.qk();
              break;
            case 117: 
              j = pq.b(parampf, 117);
              if (this.awu == null) {}
              for (i = 0;; i = this.awu.length)
              {
                localObject = new float[j + i];
                j = i;
                if (i != 0) {
                  System.arraycopy(this.awu, 0, localObject, 0, i);
                }
                for (j = i; j < localObject.length - 1; j++)
                {
                  localObject[j] = parampf.readFloat();
                  parampf.qi();
                }
              }
              localObject[j] = parampf.readFloat();
              this.awu = ((float[])localObject);
              break;
            case 114: 
              i = parampf.qp();
              k = parampf.gp(i);
              j = i / 4;
              if (this.awu == null) {}
              for (i = 0;; i = this.awu.length)
              {
                localObject = new float[j + i];
                j = i;
                if (i != 0) {
                  System.arraycopy(this.awu, 0, localObject, 0, i);
                }
                for (j = i; j < localObject.length; j++) {
                  localObject[j] = parampf.readFloat();
                }
              }
              this.awu = ((float[])localObject);
              parampf.gq(k);
            }
          }
        }
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\pd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */