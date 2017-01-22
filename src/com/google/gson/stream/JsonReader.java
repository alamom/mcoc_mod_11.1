package com.google.gson.stream;

import com.google.gson.internal.JsonReaderInternalAccess;
import com.google.gson.internal.bind.JsonTreeReader;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;

public class JsonReader
  implements Closeable
{
  private static final long MIN_INCOMPLETE_INTEGER = -922337203685477580L;
  private static final char[] NON_EXECUTE_PREFIX = ")]}'\n".toCharArray();
  private static final int NUMBER_CHAR_DECIMAL = 3;
  private static final int NUMBER_CHAR_DIGIT = 2;
  private static final int NUMBER_CHAR_EXP_DIGIT = 7;
  private static final int NUMBER_CHAR_EXP_E = 5;
  private static final int NUMBER_CHAR_EXP_SIGN = 6;
  private static final int NUMBER_CHAR_FRACTION_DIGIT = 4;
  private static final int NUMBER_CHAR_NONE = 0;
  private static final int NUMBER_CHAR_SIGN = 1;
  private static final int PEEKED_BEGIN_ARRAY = 3;
  private static final int PEEKED_BEGIN_OBJECT = 1;
  private static final int PEEKED_BUFFERED = 11;
  private static final int PEEKED_DOUBLE_QUOTED = 9;
  private static final int PEEKED_DOUBLE_QUOTED_NAME = 13;
  private static final int PEEKED_END_ARRAY = 4;
  private static final int PEEKED_END_OBJECT = 2;
  private static final int PEEKED_EOF = 17;
  private static final int PEEKED_FALSE = 6;
  private static final int PEEKED_LONG = 15;
  private static final int PEEKED_NONE = 0;
  private static final int PEEKED_NULL = 7;
  private static final int PEEKED_NUMBER = 16;
  private static final int PEEKED_SINGLE_QUOTED = 8;
  private static final int PEEKED_SINGLE_QUOTED_NAME = 12;
  private static final int PEEKED_TRUE = 5;
  private static final int PEEKED_UNQUOTED = 10;
  private static final int PEEKED_UNQUOTED_NAME = 14;
  private final char[] buffer = new char['Ѐ'];
  private final Reader in;
  private boolean lenient = false;
  private int limit = 0;
  private int lineNumber = 0;
  private int lineStart = 0;
  private int[] pathIndices;
  private String[] pathNames;
  private int peeked = 0;
  private long peekedLong;
  private int peekedNumberLength;
  private String peekedString;
  private int pos = 0;
  private int[] stack = new int[32];
  private int stackSize = 0;
  
  static
  {
    JsonReaderInternalAccess.INSTANCE = new JsonReaderInternalAccess()
    {
      public void promoteNameToValue(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        if ((paramAnonymousJsonReader instanceof JsonTreeReader)) {
          ((JsonTreeReader)paramAnonymousJsonReader).promoteNameToValue();
        }
        for (;;)
        {
          return;
          int j = paramAnonymousJsonReader.peeked;
          int i = j;
          if (j == 0) {
            i = paramAnonymousJsonReader.doPeek();
          }
          if (i == 13)
          {
            JsonReader.access$002(paramAnonymousJsonReader, 9);
          }
          else if (i == 12)
          {
            JsonReader.access$002(paramAnonymousJsonReader, 8);
          }
          else
          {
            if (i != 14) {
              break;
            }
            JsonReader.access$002(paramAnonymousJsonReader, 10);
          }
        }
        throw new IllegalStateException("Expected a name but was " + paramAnonymousJsonReader.peek() + " " + " at line " + paramAnonymousJsonReader.getLineNumber() + " column " + paramAnonymousJsonReader.getColumnNumber() + " path " + paramAnonymousJsonReader.getPath());
      }
    };
  }
  
  public JsonReader(Reader paramReader)
  {
    int[] arrayOfInt = this.stack;
    int i = this.stackSize;
    this.stackSize = (i + 1);
    arrayOfInt[i] = 6;
    this.pathNames = new String[32];
    this.pathIndices = new int[32];
    if (paramReader == null) {
      throw new NullPointerException("in == null");
    }
    this.in = paramReader;
  }
  
  private void checkLenient()
    throws IOException
  {
    if (!this.lenient) {
      throw syntaxError("Use JsonReader.setLenient(true) to accept malformed JSON");
    }
  }
  
  private void consumeNonExecutePrefix()
    throws IOException
  {
    nextNonWhitespace(true);
    this.pos -= 1;
    if ((this.pos + NON_EXECUTE_PREFIX.length > this.limit) && (!fillBuffer(NON_EXECUTE_PREFIX.length))) {}
    for (;;)
    {
      return;
      for (int i = 0;; i++)
      {
        if (i >= NON_EXECUTE_PREFIX.length) {
          break label79;
        }
        if (this.buffer[(this.pos + i)] != NON_EXECUTE_PREFIX[i]) {
          break;
        }
      }
      label79:
      this.pos += NON_EXECUTE_PREFIX.length;
    }
  }
  
  private int doPeek()
    throws IOException
  {
    int i = 4;
    int j = this.stack[(this.stackSize - 1)];
    if (j == 1)
    {
      this.stack[(this.stackSize - 1)] = 2;
      label31:
      switch (nextNonWhitespace(true))
      {
      default: 
        this.pos -= 1;
        if (this.stackSize == 1) {
          checkLenient();
        }
        i = peekKeyword();
        if (i == 0) {
          break;
        }
      }
    }
    for (;;)
    {
      return i;
      if (j == 2)
      {
        switch (nextNonWhitespace(true))
        {
        case 44: 
        default: 
          throw syntaxError("Unterminated array");
        case 93: 
          this.peeked = 4;
          break;
        case 59: 
          checkLenient();
          break;
        }
      }
      else if ((j == 3) || (j == 5))
      {
        this.stack[(this.stackSize - 1)] = 4;
        if (j == 5) {
          switch (nextNonWhitespace(true))
          {
          default: 
            throw syntaxError("Unterminated object");
          case 125: 
            this.peeked = 2;
            i = 2;
            break;
          case 59: 
            checkLenient();
          }
        } else {
          i = nextNonWhitespace(true);
        }
      }
      else
      {
        switch (i)
        {
        default: 
          checkLenient();
          this.pos -= 1;
          if (isLiteral((char)i))
          {
            i = 14;
            this.peeked = 14;
          }
          break;
        case 34: 
          i = 13;
          this.peeked = 13;
          break;
        case 39: 
          checkLenient();
          i = 12;
          this.peeked = 12;
          break;
        case 125: 
          if (j != 5)
          {
            this.peeked = 2;
            i = 2;
          }
          else
          {
            throw syntaxError("Expected name");
            throw syntaxError("Expected name");
            if (j == 4)
            {
              this.stack[(this.stackSize - 1)] = 5;
              switch (nextNonWhitespace(true))
              {
              case 58: 
              case 59: 
              case 60: 
              default: 
                throw syntaxError("Expected ':'");
              }
              checkLenient();
              if (((this.pos >= this.limit) && (!fillBuffer(1))) || (this.buffer[this.pos] != '>')) {
                break label31;
              }
              this.pos += 1;
              break label31;
            }
            if (j == 6)
            {
              if (this.lenient) {
                consumeNonExecutePrefix();
              }
              this.stack[(this.stackSize - 1)] = 7;
              break label31;
            }
            if (j == 7)
            {
              if (nextNonWhitespace(false) == -1)
              {
                i = 17;
                this.peeked = 17;
                continue;
              }
              checkLenient();
              this.pos -= 1;
              break label31;
            }
            if (j != 8) {
              break label31;
            }
            throw new IllegalStateException("JsonReader is closed");
            if (j == 1)
            {
              this.peeked = 4;
            }
            else if ((j == 1) || (j == 2))
            {
              checkLenient();
              this.pos -= 1;
              this.peeked = 7;
              i = 7;
            }
            else
            {
              throw syntaxError("Unexpected value");
              checkLenient();
              i = 8;
              this.peeked = 8;
              continue;
              if (this.stackSize == 1) {
                checkLenient();
              }
              i = 9;
              this.peeked = 9;
              continue;
              i = 3;
              this.peeked = 3;
              continue;
              this.peeked = 1;
              i = 1;
              continue;
              j = peekNumber();
              i = j;
              if (j == 0)
              {
                if (!isLiteral(this.buffer[this.pos])) {
                  throw syntaxError("Expected value");
                }
                checkLenient();
                i = 10;
                this.peeked = 10;
              }
            }
          }
          break;
        }
      }
    }
  }
  
  private boolean fillBuffer(int paramInt)
    throws IOException
  {
    boolean bool2 = false;
    char[] arrayOfChar = this.buffer;
    this.lineStart -= this.pos;
    if (this.limit != this.pos)
    {
      this.limit -= this.pos;
      System.arraycopy(arrayOfChar, this.pos, arrayOfChar, 0, this.limit);
    }
    for (;;)
    {
      this.pos = 0;
      int i;
      do
      {
        i = this.in.read(arrayOfChar, this.limit, arrayOfChar.length - this.limit);
        bool1 = bool2;
        if (i == -1) {
          break;
        }
        this.limit += i;
        i = paramInt;
        if (this.lineNumber == 0)
        {
          i = paramInt;
          if (this.lineStart == 0)
          {
            i = paramInt;
            if (this.limit > 0)
            {
              i = paramInt;
              if (arrayOfChar[0] == 65279)
              {
                this.pos += 1;
                this.lineStart += 1;
                i = paramInt + 1;
              }
            }
          }
        }
        paramInt = i;
      } while (this.limit < i);
      boolean bool1 = true;
      return bool1;
      this.limit = 0;
    }
  }
  
  private int getColumnNumber()
  {
    return this.pos - this.lineStart + 1;
  }
  
  private int getLineNumber()
  {
    return this.lineNumber + 1;
  }
  
  private boolean isLiteral(char paramChar)
    throws IOException
  {
    switch (paramChar)
    {
    }
    for (boolean bool = true;; bool = false)
    {
      return bool;
      checkLenient();
    }
  }
  
  private int nextNonWhitespace(boolean paramBoolean)
    throws IOException
  {
    char[] arrayOfChar = this.buffer;
    int i = this.pos;
    int j = this.limit;
    for (;;)
    {
      int k = j;
      int m = i;
      if (i == j)
      {
        this.pos = i;
        if (!fillBuffer(1))
        {
          if (paramBoolean) {
            throw new EOFException("End of input at line " + getLineNumber() + " column " + getColumnNumber());
          }
        }
        else
        {
          m = this.pos;
          k = this.limit;
        }
      }
      else
      {
        i = m + 1;
        j = arrayOfChar[m];
        if (j == 10)
        {
          this.lineNumber += 1;
          this.lineStart = i;
          j = k;
          continue;
        }
        if ((j == 32) || (j == 13)) {
          break label372;
        }
        if (j == 9)
        {
          j = k;
          continue;
        }
        if (j == 47)
        {
          this.pos = i;
          if (i == k)
          {
            this.pos -= 1;
            boolean bool = fillBuffer(2);
            this.pos += 1;
            if (!bool) {
              i = j;
            }
          }
        }
      }
      for (;;)
      {
        return i;
        checkLenient();
        switch (arrayOfChar[this.pos])
        {
        default: 
          i = j;
          break;
        case '*': 
          this.pos += 1;
          if (!skipTo("*/")) {
            throw syntaxError("Unterminated comment");
          }
          i = this.pos + 2;
          j = this.limit;
          break;
        case '/': 
          this.pos += 1;
          skipToEndOfLine();
          i = this.pos;
          j = this.limit;
          break;
          if (j == 35)
          {
            this.pos = i;
            checkLenient();
            skipToEndOfLine();
            i = this.pos;
            j = this.limit;
            break;
          }
          this.pos = i;
          i = j;
          continue;
          i = -1;
        }
      }
      label372:
      j = k;
    }
  }
  
  private String nextQuotedValue(char paramChar)
    throws IOException
  {
    char[] arrayOfChar = this.buffer;
    StringBuilder localStringBuilder = new StringBuilder();
    do
    {
      int i = this.pos;
      int m = this.limit;
      int k = i;
      if (i < m)
      {
        int i1 = i + 1;
        char c = arrayOfChar[i];
        if (c == paramChar)
        {
          this.pos = i1;
          localStringBuilder.append(arrayOfChar, k, i1 - k - 1);
          return localStringBuilder.toString();
        }
        int n;
        int j;
        if (c == '\\')
        {
          this.pos = i1;
          localStringBuilder.append(arrayOfChar, k, i1 - k - 1);
          localStringBuilder.append(readEscapeCharacter());
          i = this.pos;
          n = this.limit;
          j = i;
        }
        for (;;)
        {
          m = n;
          k = j;
          break;
          n = m;
          i = i1;
          j = k;
          if (c == '\n')
          {
            this.lineNumber += 1;
            this.lineStart = i1;
            n = m;
            i = i1;
            j = k;
          }
        }
      }
      localStringBuilder.append(arrayOfChar, k, i - k);
      this.pos = i;
    } while (fillBuffer(1));
    throw syntaxError("Unterminated string");
  }
  
  private String nextUnquotedValue()
    throws IOException
  {
    Object localObject1 = null;
    int i = 0;
    Object localObject2;
    int j;
    while (this.pos + i < this.limit)
    {
      localObject2 = localObject1;
      j = i;
      switch (this.buffer[(this.pos + i)])
      {
      default: 
        i++;
        break;
      case '#': 
      case '/': 
      case ';': 
      case '=': 
      case '\\': 
        checkLenient();
        j = i;
        localObject2 = localObject1;
      case '\t': 
      case '\n': 
      case '\f': 
      case '\r': 
      case ' ': 
      case ',': 
      case ':': 
      case '[': 
      case ']': 
      case '{': 
      case '}': 
        label187:
        if (localObject2 != null) {
          break label307;
        }
      }
    }
    for (localObject1 = new String(this.buffer, this.pos, j);; localObject1 = ((StringBuilder)localObject2).toString())
    {
      this.pos += j;
      return (String)localObject1;
      if (i < this.buffer.length)
      {
        localObject2 = localObject1;
        j = i;
        if (!fillBuffer(i + 1)) {
          break label187;
        }
        break;
      }
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = new StringBuilder();
      }
      ((StringBuilder)localObject2).append(this.buffer, this.pos, i);
      this.pos += i;
      j = 0;
      i = 0;
      localObject1 = localObject2;
      if (fillBuffer(1)) {
        break;
      }
      break label187;
      label307:
      ((StringBuilder)localObject2).append(this.buffer, this.pos, j);
    }
  }
  
  private int peekKeyword()
    throws IOException
  {
    int i = this.buffer[this.pos];
    String str1;
    String str2;
    int k;
    int j;
    if ((i == 116) || (i == 84))
    {
      str1 = "true";
      str2 = "TRUE";
      i = 5;
      k = str1.length();
      j = 1;
      label42:
      if (j >= k) {
        break label181;
      }
      if ((this.pos + j < this.limit) || (fillBuffer(j + 1))) {
        break label135;
      }
      i = 0;
    }
    for (;;)
    {
      return i;
      if ((i == 102) || (i == 70))
      {
        str1 = "false";
        str2 = "FALSE";
        i = 6;
        break;
      }
      if ((i == 110) || (i == 78))
      {
        str1 = "null";
        str2 = "NULL";
        i = 7;
        break;
      }
      i = 0;
      continue;
      label135:
      int m = this.buffer[(this.pos + j)];
      if ((m != str1.charAt(j)) && (m != str2.charAt(j)))
      {
        i = 0;
      }
      else
      {
        j++;
        break label42;
        label181:
        if (((this.pos + k < this.limit) || (fillBuffer(k + 1))) && (isLiteral(this.buffer[(this.pos + k)])))
        {
          i = 0;
        }
        else
        {
          this.pos += k;
          this.peeked = i;
        }
      }
    }
  }
  
  private int peekNumber()
    throws IOException
  {
    char[] arrayOfChar = this.buffer;
    int i = this.pos;
    int i3 = this.limit;
    long l1 = 0L;
    int j = 0;
    int n = 1;
    int i2 = 0;
    int k = 0;
    int i1 = i3;
    int m = i;
    if (i + k == i3) {
      if (k == arrayOfChar.length) {
        i = 0;
      }
    }
    for (;;)
    {
      label57:
      return i;
      if (!fillBuffer(k + 1))
      {
        label70:
        if ((i2 != 2) || (n == 0) || ((l1 == Long.MIN_VALUE) && (j == 0))) {
          break label571;
        }
        if (j == 0) {
          break label563;
        }
      }
      for (;;)
      {
        this.peekedLong = l1;
        this.pos += k;
        i = 15;
        this.peeked = 15;
        break label57;
        m = this.pos;
        i1 = this.limit;
        char c = arrayOfChar[(m + k)];
        int i4;
        long l2;
        switch (c)
        {
        default: 
          if ((c < '0') || (c > '9'))
          {
            if (!isLiteral(c)) {
              break label70;
            }
            i = 0;
          }
          break;
        case '-': 
          if (i2 == 0)
          {
            i4 = 1;
            i = 1;
            l2 = l1;
            i3 = n;
          }
        case '+': 
        case 'E': 
        case 'e': 
        case '.': 
          for (;;)
          {
            k++;
            n = i3;
            i3 = i1;
            i2 = i;
            j = i4;
            i = m;
            l1 = l2;
            break;
            if (i2 == 5)
            {
              i = 6;
              i3 = n;
              i4 = j;
              l2 = l1;
            }
            else
            {
              i = 0;
              break label57;
              if (i2 == 5)
              {
                i = 6;
                i3 = n;
                i4 = j;
                l2 = l1;
              }
              else
              {
                i = 0;
                break label57;
                if ((i2 == 2) || (i2 == 4))
                {
                  i = 5;
                  i3 = n;
                  i4 = j;
                  l2 = l1;
                }
                else
                {
                  i = 0;
                  break label57;
                  if (i2 == 2)
                  {
                    i = 3;
                    i3 = n;
                    i4 = j;
                    l2 = l1;
                  }
                  else
                  {
                    i = 0;
                    break label57;
                    if ((i2 == 1) || (i2 == 0))
                    {
                      l2 = -(c - '0');
                      i = 2;
                      i3 = n;
                      i4 = j;
                    }
                    else
                    {
                      if (i2 == 2)
                      {
                        if (l1 == 0L)
                        {
                          i = 0;
                          break label57;
                        }
                        l2 = 10L * l1 - (c - '0');
                        if ((l1 > -922337203685477580L) || ((l1 == -922337203685477580L) && (l2 < l1))) {}
                        for (i = 1;; i = 0)
                        {
                          i3 = n & i;
                          i = i2;
                          i4 = j;
                          break;
                        }
                      }
                      if (i2 == 3)
                      {
                        i = 4;
                        i3 = n;
                        i4 = j;
                        l2 = l1;
                      }
                      else if (i2 != 5)
                      {
                        i3 = n;
                        i = i2;
                        i4 = j;
                        l2 = l1;
                        if (i2 != 6) {}
                      }
                      else
                      {
                        i = 7;
                        i3 = n;
                        i4 = j;
                        l2 = l1;
                      }
                    }
                  }
                }
              }
            }
          }
          label563:
          l1 = -l1;
        }
      }
      label571:
      if ((i2 == 2) || (i2 == 4) || (i2 == 7))
      {
        this.peekedNumberLength = k;
        i = 16;
        this.peeked = 16;
      }
      else
      {
        i = 0;
      }
    }
  }
  
  private void push(int paramInt)
  {
    if (this.stackSize == this.stack.length)
    {
      int[] arrayOfInt2 = new int[this.stackSize * 2];
      arrayOfInt1 = new int[this.stackSize * 2];
      String[] arrayOfString = new String[this.stackSize * 2];
      System.arraycopy(this.stack, 0, arrayOfInt2, 0, this.stackSize);
      System.arraycopy(this.pathIndices, 0, arrayOfInt1, 0, this.stackSize);
      System.arraycopy(this.pathNames, 0, arrayOfString, 0, this.stackSize);
      this.stack = arrayOfInt2;
      this.pathIndices = arrayOfInt1;
      this.pathNames = arrayOfString;
    }
    int[] arrayOfInt1 = this.stack;
    int i = this.stackSize;
    this.stackSize = (i + 1);
    arrayOfInt1[i] = paramInt;
  }
  
  private char readEscapeCharacter()
    throws IOException
  {
    if ((this.pos == this.limit) && (!fillBuffer(1))) {
      throw syntaxError("Unterminated escape sequence");
    }
    char[] arrayOfChar = this.buffer;
    int i = this.pos;
    this.pos = (i + 1);
    char c = arrayOfChar[i];
    switch (c)
    {
    }
    for (;;)
    {
      return c;
      if ((this.pos + 4 > this.limit) && (!fillBuffer(4))) {
        throw syntaxError("Unterminated escape sequence");
      }
      c = '\000';
      int j = this.pos;
      i = j;
      if (i < j + 4)
      {
        int m = this.buffer[i];
        int k = (char)(c << '\004');
        if ((m >= 48) && (m <= 57)) {
          c = (char)(m - 48 + k);
        }
        for (;;)
        {
          i++;
          break;
          if ((m >= 97) && (m <= 102))
          {
            c = (char)(m - 97 + 10 + k);
          }
          else
          {
            if ((m < 65) || (m > 70)) {
              break label267;
            }
            c = (char)(m - 65 + 10 + k);
          }
        }
        label267:
        throw new NumberFormatException("\\u" + new String(this.buffer, this.pos, 4));
      }
      this.pos += 4;
      continue;
      c = '\t';
      continue;
      c = '\b';
      continue;
      c = '\n';
      continue;
      c = '\r';
      continue;
      c = '\f';
      continue;
      this.lineNumber += 1;
      this.lineStart = this.pos;
    }
  }
  
  private void skipQuotedValue(char paramChar)
    throws IOException
  {
    char[] arrayOfChar = this.buffer;
    do
    {
      int i = this.pos;
      int j = this.limit;
      if (i < j)
      {
        int m = i + 1;
        char c = arrayOfChar[i];
        if (c == paramChar)
        {
          this.pos = m;
          return;
        }
        int k;
        if (c == '\\')
        {
          this.pos = m;
          readEscapeCharacter();
          i = this.pos;
          k = this.limit;
        }
        for (;;)
        {
          j = k;
          break;
          k = j;
          i = m;
          if (c == '\n')
          {
            this.lineNumber += 1;
            this.lineStart = m;
            k = j;
            i = m;
          }
        }
      }
      this.pos = i;
    } while (fillBuffer(1));
    throw syntaxError("Unterminated string");
  }
  
  private boolean skipTo(String paramString)
    throws IOException
  {
    while ((this.pos + paramString.length() <= this.limit) || (fillBuffer(paramString.length()))) {
      if (this.buffer[this.pos] == '\n')
      {
        this.lineNumber += 1;
        this.lineStart = (this.pos + 1);
        this.pos += 1;
      }
      else
      {
        for (int i = 0;; i++)
        {
          if (i >= paramString.length()) {
            break label109;
          }
          if (this.buffer[(this.pos + i)] != paramString.charAt(i)) {
            break;
          }
        }
      }
    }
    label109:
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  private void skipToEndOfLine()
    throws IOException
  {
    int i;
    if ((this.pos < this.limit) || (fillBuffer(1)))
    {
      char[] arrayOfChar = this.buffer;
      i = this.pos;
      this.pos = (i + 1);
      i = arrayOfChar[i];
      if (i != 10) {
        break label65;
      }
      this.lineNumber += 1;
      this.lineStart = this.pos;
    }
    for (;;)
    {
      return;
      label65:
      if (i != 13) {
        break;
      }
    }
  }
  
  private void skipUnquotedValue()
    throws IOException
  {
    int i = 0;
    while (this.pos + i < this.limit) {
      switch (this.buffer[(this.pos + i)])
      {
      default: 
        i++;
        break;
      case '#': 
      case '/': 
      case ';': 
      case '=': 
      case '\\': 
        checkLenient();
      case '\t': 
      case '\n': 
      case '\f': 
      case '\r': 
      case ' ': 
      case ',': 
      case ':': 
      case '[': 
      case ']': 
      case '{': 
      case '}': 
        this.pos += i;
      }
    }
    for (;;)
    {
      return;
      this.pos += i;
      if (fillBuffer(1)) {
        break;
      }
    }
  }
  
  private IOException syntaxError(String paramString)
    throws IOException
  {
    throw new MalformedJsonException(paramString + " at line " + getLineNumber() + " column " + getColumnNumber() + " path " + getPath());
  }
  
  public void beginArray()
    throws IOException
  {
    int j = this.peeked;
    int i = j;
    if (j == 0) {
      i = doPeek();
    }
    if (i == 3)
    {
      push(1);
      this.pathIndices[(this.stackSize - 1)] = 0;
      this.peeked = 0;
      return;
    }
    throw new IllegalStateException("Expected BEGIN_ARRAY but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber() + " path " + getPath());
  }
  
  public void beginObject()
    throws IOException
  {
    int j = this.peeked;
    int i = j;
    if (j == 0) {
      i = doPeek();
    }
    if (i == 1)
    {
      push(3);
      this.peeked = 0;
      return;
    }
    throw new IllegalStateException("Expected BEGIN_OBJECT but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber() + " path " + getPath());
  }
  
  public void close()
    throws IOException
  {
    this.peeked = 0;
    this.stack[0] = 8;
    this.stackSize = 1;
    this.in.close();
  }
  
  public void endArray()
    throws IOException
  {
    int j = this.peeked;
    int i = j;
    if (j == 0) {
      i = doPeek();
    }
    if (i == 4)
    {
      this.stackSize -= 1;
      int[] arrayOfInt = this.pathIndices;
      i = this.stackSize - 1;
      arrayOfInt[i] += 1;
      this.peeked = 0;
      return;
    }
    throw new IllegalStateException("Expected END_ARRAY but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber() + " path " + getPath());
  }
  
  public void endObject()
    throws IOException
  {
    int j = this.peeked;
    int i = j;
    if (j == 0) {
      i = doPeek();
    }
    if (i == 2)
    {
      this.stackSize -= 1;
      this.pathNames[this.stackSize] = null;
      int[] arrayOfInt = this.pathIndices;
      i = this.stackSize - 1;
      arrayOfInt[i] += 1;
      this.peeked = 0;
      return;
    }
    throw new IllegalStateException("Expected END_OBJECT but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber() + " path " + getPath());
  }
  
  public String getPath()
  {
    StringBuilder localStringBuilder = new StringBuilder().append('$');
    int i = 0;
    int j = this.stackSize;
    if (i < j)
    {
      switch (this.stack[i])
      {
      }
      for (;;)
      {
        i++;
        break;
        localStringBuilder.append('[').append(this.pathIndices[i]).append(']');
        continue;
        localStringBuilder.append('.');
        if (this.pathNames[i] != null) {
          localStringBuilder.append(this.pathNames[i]);
        }
      }
    }
    return localStringBuilder.toString();
  }
  
  public boolean hasNext()
    throws IOException
  {
    int j = this.peeked;
    int i = j;
    if (j == 0) {
      i = doPeek();
    }
    if ((i != 2) && (i != 4)) {}
    for (boolean bool = true;; bool = false) {
      return bool;
    }
  }
  
  public final boolean isLenient()
  {
    return this.lenient;
  }
  
  public boolean nextBoolean()
    throws IOException
  {
    boolean bool = false;
    int j = this.peeked;
    int i = j;
    if (j == 0) {
      i = doPeek();
    }
    int[] arrayOfInt;
    if (i == 5)
    {
      this.peeked = 0;
      arrayOfInt = this.pathIndices;
      i = this.stackSize - 1;
      arrayOfInt[i] += 1;
      bool = true;
    }
    for (;;)
    {
      return bool;
      if (i != 6) {
        break;
      }
      this.peeked = 0;
      arrayOfInt = this.pathIndices;
      i = this.stackSize - 1;
      arrayOfInt[i] += 1;
    }
    throw new IllegalStateException("Expected a boolean but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber() + " path " + getPath());
  }
  
  public double nextDouble()
    throws IOException
  {
    int j = this.peeked;
    int i = j;
    if (j == 0) {
      i = doPeek();
    }
    int[] arrayOfInt;
    double d;
    if (i == 15)
    {
      this.peeked = 0;
      arrayOfInt = this.pathIndices;
      i = this.stackSize - 1;
      arrayOfInt[i] += 1;
      d = this.peekedLong;
    }
    for (;;)
    {
      return d;
      if (i == 16)
      {
        this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
        this.pos += this.peekedNumberLength;
      }
      do
      {
        for (;;)
        {
          this.peeked = 11;
          d = Double.parseDouble(this.peekedString);
          if ((this.lenient) || ((!Double.isNaN(d)) && (!Double.isInfinite(d)))) {
            break label347;
          }
          throw new MalformedJsonException("JSON forbids NaN and infinities: " + d + " at line " + getLineNumber() + " column " + getColumnNumber() + " path " + getPath());
          if ((i == 8) || (i == 9))
          {
            if (i == 8) {}
            for (char c = '\'';; c = '"')
            {
              this.peekedString = nextQuotedValue(c);
              break;
            }
          }
          if (i != 10) {
            break;
          }
          this.peekedString = nextUnquotedValue();
        }
      } while (i == 11);
      throw new IllegalStateException("Expected a double but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber() + " path " + getPath());
      label347:
      this.peekedString = null;
      this.peeked = 0;
      arrayOfInt = this.pathIndices;
      i = this.stackSize - 1;
      arrayOfInt[i] += 1;
    }
  }
  
  public int nextInt()
    throws IOException
  {
    int j = this.peeked;
    int i = j;
    if (j == 0) {
      i = doPeek();
    }
    int[] arrayOfInt;
    if (i == 15)
    {
      i = (int)this.peekedLong;
      if (this.peekedLong != i) {
        throw new NumberFormatException("Expected an int but was " + this.peekedLong + " at line " + getLineNumber() + " column " + getColumnNumber() + " path " + getPath());
      }
      this.peeked = 0;
      arrayOfInt = this.pathIndices;
      j = this.stackSize - 1;
      arrayOfInt[j] += 1;
      return i;
    }
    if (i == 16)
    {
      this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
      this.pos += this.peekedNumberLength;
    }
    for (;;)
    {
      this.peeked = 11;
      double d = Double.parseDouble(this.peekedString);
      i = (int)d;
      if (i != d)
      {
        throw new NumberFormatException("Expected an int but was " + this.peekedString + " at line " + getLineNumber() + " column " + getColumnNumber() + " path " + getPath());
        if ((i == 8) || (i == 9))
        {
          if (i == 8) {}
          for (char c = '\'';; c = '"')
          {
            this.peekedString = nextQuotedValue(c);
            try
            {
              i = Integer.parseInt(this.peekedString);
              this.peeked = 0;
              arrayOfInt = this.pathIndices;
              j = this.stackSize - 1;
              arrayOfInt[j] += 1;
            }
            catch (NumberFormatException localNumberFormatException) {}
          }
        }
        throw new IllegalStateException("Expected an int but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber() + " path " + getPath());
      }
      this.peekedString = null;
      this.peeked = 0;
      arrayOfInt = this.pathIndices;
      j = this.stackSize - 1;
      arrayOfInt[j] += 1;
      break;
    }
  }
  
  public long nextLong()
    throws IOException
  {
    int j = this.peeked;
    int i = j;
    if (j == 0) {
      i = doPeek();
    }
    int[] arrayOfInt1;
    long l;
    if (i == 15)
    {
      this.peeked = 0;
      arrayOfInt1 = this.pathIndices;
      i = this.stackSize - 1;
      arrayOfInt1[i] += 1;
      l = this.peekedLong;
    }
    for (;;)
    {
      return l;
      if (i == 16)
      {
        this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
        this.pos += this.peekedNumberLength;
        label111:
        this.peeked = 11;
        double d = Double.parseDouble(this.peekedString);
        l = d;
        if (l != d) {
          throw new NumberFormatException("Expected a long but was " + this.peekedString + " at line " + getLineNumber() + " column " + getColumnNumber() + " path " + getPath());
        }
      }
      else
      {
        if ((i == 8) || (i == 9))
        {
          if (i == 8) {}
          for (char c = '\'';; c = '"')
          {
            this.peekedString = nextQuotedValue(c);
            try
            {
              l = Long.parseLong(this.peekedString);
              this.peeked = 0;
              arrayOfInt1 = this.pathIndices;
              i = this.stackSize - 1;
              arrayOfInt1[i] += 1;
            }
            catch (NumberFormatException localNumberFormatException) {}
            break label111;
          }
        }
        throw new IllegalStateException("Expected a long but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber() + " path " + getPath());
      }
      this.peekedString = null;
      this.peeked = 0;
      int[] arrayOfInt2 = this.pathIndices;
      i = this.stackSize - 1;
      arrayOfInt2[i] += 1;
    }
  }
  
  public String nextName()
    throws IOException
  {
    int j = this.peeked;
    int i = j;
    if (j == 0) {
      i = doPeek();
    }
    String str;
    if (i == 14) {
      str = nextUnquotedValue();
    }
    for (;;)
    {
      this.peeked = 0;
      this.pathNames[(this.stackSize - 1)] = str;
      return str;
      if (i == 12)
      {
        str = nextQuotedValue('\'');
      }
      else
      {
        if (i != 13) {
          break;
        }
        str = nextQuotedValue('"');
      }
    }
    throw new IllegalStateException("Expected a name but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber() + " path " + getPath());
  }
  
  public void nextNull()
    throws IOException
  {
    int j = this.peeked;
    int i = j;
    if (j == 0) {
      i = doPeek();
    }
    if (i == 7)
    {
      this.peeked = 0;
      int[] arrayOfInt = this.pathIndices;
      i = this.stackSize - 1;
      arrayOfInt[i] += 1;
      return;
    }
    throw new IllegalStateException("Expected null but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber() + " path " + getPath());
  }
  
  public String nextString()
    throws IOException
  {
    int j = this.peeked;
    int i = j;
    if (j == 0) {
      i = doPeek();
    }
    String str;
    if (i == 10) {
      str = nextUnquotedValue();
    }
    for (;;)
    {
      this.peeked = 0;
      int[] arrayOfInt = this.pathIndices;
      i = this.stackSize - 1;
      arrayOfInt[i] += 1;
      return str;
      if (i == 8)
      {
        str = nextQuotedValue('\'');
      }
      else if (i == 9)
      {
        str = nextQuotedValue('"');
      }
      else if (i == 11)
      {
        str = this.peekedString;
        this.peekedString = null;
      }
      else if (i == 15)
      {
        str = Long.toString(this.peekedLong);
      }
      else
      {
        if (i != 16) {
          break;
        }
        str = new String(this.buffer, this.pos, this.peekedNumberLength);
        this.pos += this.peekedNumberLength;
      }
    }
    throw new IllegalStateException("Expected a string but was " + peek() + " at line " + getLineNumber() + " column " + getColumnNumber() + " path " + getPath());
  }
  
  public JsonToken peek()
    throws IOException
  {
    int j = this.peeked;
    int i = j;
    if (j == 0) {
      i = doPeek();
    }
    JsonToken localJsonToken;
    switch (i)
    {
    default: 
      throw new AssertionError();
    case 1: 
      localJsonToken = JsonToken.BEGIN_OBJECT;
    }
    for (;;)
    {
      return localJsonToken;
      localJsonToken = JsonToken.END_OBJECT;
      continue;
      localJsonToken = JsonToken.BEGIN_ARRAY;
      continue;
      localJsonToken = JsonToken.END_ARRAY;
      continue;
      localJsonToken = JsonToken.NAME;
      continue;
      localJsonToken = JsonToken.BOOLEAN;
      continue;
      localJsonToken = JsonToken.NULL;
      continue;
      localJsonToken = JsonToken.STRING;
      continue;
      localJsonToken = JsonToken.NUMBER;
      continue;
      localJsonToken = JsonToken.END_DOCUMENT;
    }
  }
  
  public final void setLenient(boolean paramBoolean)
  {
    this.lenient = paramBoolean;
  }
  
  public void skipValue()
    throws IOException
  {
    int j = 0;
    int i = this.peeked;
    int k = i;
    if (i == 0) {
      k = doPeek();
    }
    if (k == 3)
    {
      push(1);
      i = j + 1;
    }
    for (;;)
    {
      this.peeked = 0;
      j = i;
      if (i != 0) {
        break;
      }
      int[] arrayOfInt = this.pathIndices;
      i = this.stackSize - 1;
      arrayOfInt[i] += 1;
      this.pathNames[(this.stackSize - 1)] = "null";
      return;
      if (k == 1)
      {
        push(3);
        i = j + 1;
      }
      else if (k == 4)
      {
        this.stackSize -= 1;
        i = j - 1;
      }
      else if (k == 2)
      {
        this.stackSize -= 1;
        i = j - 1;
      }
      else if ((k == 14) || (k == 10))
      {
        skipUnquotedValue();
        i = j;
      }
      else if ((k == 8) || (k == 12))
      {
        skipQuotedValue('\'');
        i = j;
      }
      else if ((k == 9) || (k == 13))
      {
        skipQuotedValue('"');
        i = j;
      }
      else
      {
        i = j;
        if (k == 16)
        {
          this.pos += this.peekedNumberLength;
          i = j;
        }
      }
    }
  }
  
  public String toString()
  {
    return getClass().getSimpleName() + " at line " + getLineNumber() + " column " + getColumnNumber();
  }
}


/* Location:              C:\tools\androidhack\marvel_bitva_chempionov_v11.1.0_mod_lenov.ru\classes.jar!\com\google\gson\stream\JsonReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */