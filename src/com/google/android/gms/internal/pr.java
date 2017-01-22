package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

public abstract interface pr
{
  public static final class a
    extends ph<a>
  {
    public String[] axe;
    public String[] axf;
    public int[] axg;
    
    public a()
    {
      qJ();
    }
    
    public void a(pg parampg)
      throws IOException
    {
      int j = 0;
      int i;
      String str;
      if ((this.axe != null) && (this.axe.length > 0)) {
        for (i = 0; i < this.axe.length; i++)
        {
          str = this.axe[i];
          if (str != null) {
            parampg.b(1, str);
          }
        }
      }
      if ((this.axf != null) && (this.axf.length > 0)) {
        for (i = 0; i < this.axf.length; i++)
        {
          str = this.axf[i];
          if (str != null) {
            parampg.b(2, str);
          }
        }
      }
      if ((this.axg != null) && (this.axg.length > 0)) {
        for (i = j; i < this.axg.length; i++) {
          parampg.s(3, this.axg[i]);
        }
      }
      super.a(parampg);
    }
    
    protected int c()
    {
      int i2 = 0;
      int i1 = super.c();
      int j;
      int m;
      int k;
      String str;
      int n;
      if ((this.axe != null) && (this.axe.length > 0))
      {
        j = 0;
        m = 0;
        for (k = 0; j < this.axe.length; k = i)
        {
          str = this.axe[j];
          n = m;
          i = k;
          if (str != null)
          {
            i = k + 1;
            n = m + pg.di(str);
          }
          j++;
          m = n;
        }
      }
      for (int i = i1 + m + k * 1;; i = i1)
      {
        j = i;
        if (this.axf != null)
        {
          j = i;
          if (this.axf.length > 0)
          {
            k = 0;
            n = 0;
            for (m = 0; k < this.axf.length; m = j)
            {
              str = this.axf[k];
              i1 = n;
              j = m;
              if (str != null)
              {
                j = m + 1;
                i1 = n + pg.di(str);
              }
              k++;
              n = i1;
            }
            j = i + n + m * 1;
          }
        }
        i = j;
        if (this.axg != null)
        {
          i = j;
          if (this.axg.length > 0)
          {
            k = 0;
            for (i = i2; i < this.axg.length; i++) {
              k += pg.gw(this.axg[i]);
            }
            i = j + k + this.axg.length * 1;
          }
        }
        return i;
      }
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
          if (pl.equals(this.axe, ((a)paramObject).axe))
          {
            bool1 = bool2;
            if (pl.equals(this.axf, ((a)paramObject).axf))
            {
              bool1 = bool2;
              if (pl.equals(this.axg, ((a)paramObject).axg)) {
                bool1 = a((ph)paramObject);
              }
            }
          }
        }
      }
    }
    
    public int hashCode()
    {
      return (((pl.hashCode(this.axe) + 527) * 31 + pl.hashCode(this.axf)) * 31 + pl.hashCode(this.axg)) * 31 + qz();
    }
    
    public a qJ()
    {
      this.axe = pq.axb;
      this.axf = pq.axb;
      this.axg = pq.awW;
      this.awJ = null;
      this.awU = -1;
      return this;
    }
    
    public a v(pf parampf)
      throws IOException
    {
      for (;;)
      {
        int i = parampf.qi();
        int j;
        Object localObject;
        switch (i)
        {
        default: 
          if (a(parampf, i)) {}
          break;
        case 0: 
          return this;
        case 10: 
          j = pq.b(parampf, 10);
          if (this.axe == null) {}
          for (i = 0;; i = this.axe.length)
          {
            localObject = new String[j + i];
            j = i;
            if (i != 0) {
              System.arraycopy(this.axe, 0, localObject, 0, i);
            }
            for (j = i; j < localObject.length - 1; j++)
            {
              localObject[j] = parampf.readString();
              parampf.qi();
            }
          }
          localObject[j] = parampf.readString();
          this.axe = ((String[])localObject);
          break;
        case 18: 
          j = pq.b(parampf, 18);
          if (this.axf == null) {}
          for (i = 0;; i = this.axf.length)
          {
            localObject = new String[j + i];
            j = i;
            if (i != 0) {
              System.arraycopy(this.axf, 0, localObject, 0, i);
            }
            for (j = i; j < localObject.length - 1; j++)
            {
              localObject[j] = parampf.readString();
              parampf.qi();
            }
          }
          localObject[j] = parampf.readString();
          this.axf = ((String[])localObject);
          break;
        case 24: 
          j = pq.b(parampf, 24);
          if (this.axg == null) {}
          for (i = 0;; i = this.axg.length)
          {
            localObject = new int[j + i];
            j = i;
            if (i != 0) {
              System.arraycopy(this.axg, 0, localObject, 0, i);
            }
            for (j = i; j < localObject.length - 1; j++)
            {
              localObject[j] = parampf.ql();
              parampf.qi();
            }
          }
          localObject[j] = parampf.ql();
          this.axg = ((int[])localObject);
          break;
        case 26: 
          int k = parampf.gp(parampf.qp());
          i = parampf.getPosition();
          for (j = 0; parampf.qu() > 0; j++) {
            parampf.ql();
          }
          parampf.gr(i);
          if (this.axg == null) {}
          for (i = 0;; i = this.axg.length)
          {
            localObject = new int[j + i];
            j = i;
            if (i != 0) {
              System.arraycopy(this.axg, 0, localObject, 0, i);
            }
            for (j = i; j < localObject.length; j++) {
              localObject[j] = parampf.ql();
            }
          }
          this.axg = ((int[])localObject);
          parampf.gq(k);
        }
      }
    }
  }
  
  public static final class b
    extends ph<b>
  {
    public int axh;
    public String axi;
    public String version;
    
    public b()
    {
      qK();
    }
    
    public void a(pg parampg)
      throws IOException
    {
      if (this.axh != 0) {
        parampg.s(1, this.axh);
      }
      if (!this.axi.equals("")) {
        parampg.b(2, this.axi);
      }
      if (!this.version.equals("")) {
        parampg.b(3, this.version);
      }
      super.a(parampg);
    }
    
    protected int c()
    {
      int j = super.c();
      int i = j;
      if (this.axh != 0) {
        i = j + pg.u(1, this.axh);
      }
      j = i;
      if (!this.axi.equals("")) {
        j = i + pg.j(2, this.axi);
      }
      i = j;
      if (!this.version.equals("")) {
        i = j + pg.j(3, this.version);
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
        if ((paramObject instanceof b))
        {
          paramObject = (b)paramObject;
          bool1 = bool2;
          if (this.axh == ((b)paramObject).axh)
          {
            if (this.axi == null)
            {
              bool1 = bool2;
              if (((b)paramObject).axi != null) {
                continue;
              }
              label54:
              if (this.version != null) {
                break label98;
              }
              bool1 = bool2;
              if (((b)paramObject).version != null) {
                continue;
              }
            }
            label98:
            while (this.version.equals(((b)paramObject).version))
            {
              bool1 = a((ph)paramObject);
              break;
              if (this.axi.equals(((b)paramObject).axi)) {
                break label54;
              }
              bool1 = bool2;
              break;
            }
            bool1 = bool2;
          }
        }
      }
    }
    
    public int hashCode()
    {
      int j = 0;
      int k = this.axh;
      int i;
      if (this.axi == null)
      {
        i = 0;
        if (this.version != null) {
          break label58;
        }
      }
      for (;;)
      {
        return ((i + (k + 527) * 31) * 31 + j) * 31 + qz();
        i = this.axi.hashCode();
        break;
        label58:
        j = this.version.hashCode();
      }
    }
    
    public b qK()
    {
      this.axh = 0;
      this.axi = "";
      this.version = "";
      this.awJ = null;
      this.awU = -1;
      return this;
    }
    
    public b w(pf parampf)
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
          case 0: 
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
          case 16: 
          case 17: 
          case 18: 
          case 19: 
          case 20: 
          case 21: 
          case 22: 
            this.axh = i;
          }
          break;
        case 18: 
          this.axi = parampf.readString();
          break;
        case 26: 
          this.version = parampf.readString();
        }
      }
    }
  }
  
  public static final class c
    extends ph<c>
  {
    public long axj;
    public int axk;
    public int axl;
    public boolean axm;
    public pr.d[] axn;
    public pr.b axo;
    public byte[] axp;
    public byte[] axq;
    public byte[] axr;
    public pr.a axs;
    public String axt;
    public String tag;
    
    public c()
    {
      qL();
    }
    
    public void a(pg parampg)
      throws IOException
    {
      if (this.axj != 0L) {
        parampg.b(1, this.axj);
      }
      if (!this.tag.equals("")) {
        parampg.b(2, this.tag);
      }
      if ((this.axn != null) && (this.axn.length > 0)) {
        for (int i = 0; i < this.axn.length; i++)
        {
          pr.d locald = this.axn[i];
          if (locald != null) {
            parampg.a(3, locald);
          }
        }
      }
      if (!Arrays.equals(this.axp, pq.axd)) {
        parampg.a(6, this.axp);
      }
      if (this.axs != null) {
        parampg.a(7, this.axs);
      }
      if (!Arrays.equals(this.axq, pq.axd)) {
        parampg.a(8, this.axq);
      }
      if (this.axo != null) {
        parampg.a(9, this.axo);
      }
      if (this.axm) {
        parampg.b(10, this.axm);
      }
      if (this.axk != 0) {
        parampg.s(11, this.axk);
      }
      if (this.axl != 0) {
        parampg.s(12, this.axl);
      }
      if (!Arrays.equals(this.axr, pq.axd)) {
        parampg.a(13, this.axr);
      }
      if (!this.axt.equals("")) {
        parampg.b(14, this.axt);
      }
      super.a(parampg);
    }
    
    protected int c()
    {
      int i = super.c();
      int j = i;
      if (this.axj != 0L) {
        j = i + pg.d(1, this.axj);
      }
      i = j;
      if (!this.tag.equals("")) {
        i = j + pg.j(2, this.tag);
      }
      j = i;
      if (this.axn != null)
      {
        j = i;
        if (this.axn.length > 0)
        {
          j = 0;
          while (j < this.axn.length)
          {
            pr.d locald = this.axn[j];
            k = i;
            if (locald != null) {
              k = i + pg.c(3, locald);
            }
            j++;
            i = k;
          }
          j = i;
        }
      }
      i = j;
      if (!Arrays.equals(this.axp, pq.axd)) {
        i = j + pg.b(6, this.axp);
      }
      j = i;
      if (this.axs != null) {
        j = i + pg.c(7, this.axs);
      }
      int k = j;
      if (!Arrays.equals(this.axq, pq.axd)) {
        k = j + pg.b(8, this.axq);
      }
      i = k;
      if (this.axo != null) {
        i = k + pg.c(9, this.axo);
      }
      j = i;
      if (this.axm) {
        j = i + pg.c(10, this.axm);
      }
      i = j;
      if (this.axk != 0) {
        i = j + pg.u(11, this.axk);
      }
      j = i;
      if (this.axl != 0) {
        j = i + pg.u(12, this.axl);
      }
      i = j;
      if (!Arrays.equals(this.axr, pq.axd)) {
        i = j + pg.b(13, this.axr);
      }
      j = i;
      if (!this.axt.equals("")) {
        j = i + pg.j(14, this.axt);
      }
      return j;
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
        if ((paramObject instanceof c))
        {
          paramObject = (c)paramObject;
          bool1 = bool2;
          if (this.axj == ((c)paramObject).axj)
          {
            if (this.tag == null)
            {
              bool1 = bool2;
              if (((c)paramObject).tag != null) {
                continue;
              }
              label55:
              bool1 = bool2;
              if (this.axk != ((c)paramObject).axk) {
                continue;
              }
              bool1 = bool2;
              if (this.axl != ((c)paramObject).axl) {
                continue;
              }
              bool1 = bool2;
              if (this.axm != ((c)paramObject).axm) {
                continue;
              }
              bool1 = bool2;
              if (!pl.equals(this.axn, ((c)paramObject).axn)) {
                continue;
              }
              if (this.axo != null) {
                break label234;
              }
              bool1 = bool2;
              if (((c)paramObject).axo != null) {
                continue;
              }
              label126:
              bool1 = bool2;
              if (!Arrays.equals(this.axp, ((c)paramObject).axp)) {
                continue;
              }
              bool1 = bool2;
              if (!Arrays.equals(this.axq, ((c)paramObject).axq)) {
                continue;
              }
              bool1 = bool2;
              if (!Arrays.equals(this.axr, ((c)paramObject).axr)) {
                continue;
              }
              if (this.axs != null) {
                break label253;
              }
              bool1 = bool2;
              if (((c)paramObject).axs != null) {
                continue;
              }
              label190:
              if (this.axt != null) {
                break label272;
              }
              bool1 = bool2;
              if (((c)paramObject).axt != null) {
                continue;
              }
            }
            label234:
            label253:
            label272:
            while (this.axt.equals(((c)paramObject).axt))
            {
              bool1 = a((ph)paramObject);
              break;
              if (this.tag.equals(((c)paramObject).tag)) {
                break label55;
              }
              bool1 = bool2;
              break;
              if (this.axo.equals(((c)paramObject).axo)) {
                break label126;
              }
              bool1 = bool2;
              break;
              if (this.axs.equals(((c)paramObject).axs)) {
                break label190;
              }
              bool1 = bool2;
              break;
            }
            bool1 = bool2;
          }
        }
      }
    }
    
    public int hashCode()
    {
      int n = 0;
      int i1 = (int)(this.axj ^ this.axj >>> 32);
      int i;
      int i3;
      int i2;
      int j;
      label50:
      int i4;
      int k;
      label68:
      int i7;
      int i6;
      int i5;
      int m;
      if (this.tag == null)
      {
        i = 0;
        i3 = this.axk;
        i2 = this.axl;
        if (!this.axm) {
          break label201;
        }
        j = 1231;
        i4 = pl.hashCode(this.axn);
        if (this.axo != null) {
          break label208;
        }
        k = 0;
        i7 = Arrays.hashCode(this.axp);
        i6 = Arrays.hashCode(this.axq);
        i5 = Arrays.hashCode(this.axr);
        if (this.axs != null) {
          break label219;
        }
        m = 0;
        label105:
        if (this.axt != null) {
          break label231;
        }
      }
      for (;;)
      {
        return ((m + ((((k + ((j + (((i + (i1 + 527) * 31) * 31 + i3) * 31 + i2) * 31) * 31 + i4) * 31) * 31 + i7) * 31 + i6) * 31 + i5) * 31) * 31 + n) * 31 + qz();
        i = this.tag.hashCode();
        break;
        label201:
        j = 1237;
        break label50;
        label208:
        k = this.axo.hashCode();
        break label68;
        label219:
        m = this.axs.hashCode();
        break label105;
        label231:
        n = this.axt.hashCode();
      }
    }
    
    public c qL()
    {
      this.axj = 0L;
      this.tag = "";
      this.axk = 0;
      this.axl = 0;
      this.axm = false;
      this.axn = pr.d.qM();
      this.axo = null;
      this.axp = pq.axd;
      this.axq = pq.axd;
      this.axr = pq.axd;
      this.axs = null;
      this.axt = "";
      this.awJ = null;
      this.awU = -1;
      return this;
    }
    
    public c x(pf parampf)
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
          this.axj = parampf.qk();
          break;
        case 18: 
          this.tag = parampf.readString();
          break;
        case 26: 
          int j = pq.b(parampf, 26);
          if (this.axn == null) {}
          pr.d[] arrayOfd;
          for (i = 0;; i = this.axn.length)
          {
            arrayOfd = new pr.d[j + i];
            j = i;
            if (i != 0) {
              System.arraycopy(this.axn, 0, arrayOfd, 0, i);
            }
            for (j = i; j < arrayOfd.length - 1; j++)
            {
              arrayOfd[j] = new pr.d();
              parampf.a(arrayOfd[j]);
              parampf.qi();
            }
          }
          arrayOfd[j] = new pr.d();
          parampf.a(arrayOfd[j]);
          this.axn = arrayOfd;
          break;
        case 50: 
          this.axp = parampf.readBytes();
          break;
        case 58: 
          if (this.axs == null) {
            this.axs = new pr.a();
          }
          parampf.a(this.axs);
          break;
        case 66: 
          this.axq = parampf.readBytes();
          break;
        case 74: 
          if (this.axo == null) {
            this.axo = new pr.b();
          }
          parampf.a(this.axo);
          break;
        case 80: 
          this.axm = parampf.qm();
          break;
        case 88: 
          this.axk = parampf.ql();
          break;
        case 96: 
          this.axl = parampf.ql();
          break;
        case 106: 
          this.axr = parampf.readBytes();
          break;
        case 114: 
          this.axt = parampf.readString();
        }
      }
    }
  }
  
  public static final class d
    extends ph<d>
  {
    private static volatile d[] axu;
    public String fv;
    public String value;
    
    public d()
    {
      qN();
    }
    
    public static d[] qM()
    {
      if (axu == null) {}
      synchronized (pl.awT)
      {
        if (axu == null) {
          axu = new d[0];
        }
        return axu;
      }
    }
    
    public void a(pg parampg)
      throws IOException
    {
      if (!this.fv.equals("")) {
        parampg.b(1, this.fv);
      }
      if (!this.value.equals("")) {
        parampg.b(2, this.value);
      }
      super.a(parampg);
    }
    
    protected int c()
    {
      int j = super.c();
      int i = j;
      if (!this.fv.equals("")) {
        i = j + pg.j(1, this.fv);
      }
      j = i;
      if (!this.value.equals("")) {
        j = i + pg.j(2, this.value);
      }
      return j;
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
        if ((paramObject instanceof d))
        {
          paramObject = (d)paramObject;
          if (this.fv == null)
          {
            bool1 = bool2;
            if (((d)paramObject).fv != null) {
              continue;
            }
            label41:
            if (this.value != null) {
              break label85;
            }
            bool1 = bool2;
            if (((d)paramObject).value != null) {
              continue;
            }
          }
          label85:
          while (this.value.equals(((d)paramObject).value))
          {
            bool1 = a((ph)paramObject);
            break;
            if (this.fv.equals(((d)paramObject).fv)) {
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
      if (this.fv == null)
      {
        i = 0;
        if (this.value != null) {
          break label48;
        }
      }
      for (;;)
      {
        return ((i + 527) * 31 + j) * 31 + qz();
        i = this.fv.hashCode();
        break;
        label48:
        j = this.value.hashCode();
      }
    }
    
    public d qN()
    {
      this.fv = "";
      this.value = "";
      this.awJ = null;
      this.awU = -1;
      return this;
    }
    
    public d y(pf parampf)
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
          this.fv = parampf.readString();
          break;
        case 18: 
          this.value = parampf.readString();
        }
      }
    }
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\android\gms\internal\pr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */