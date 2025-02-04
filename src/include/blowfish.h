
/*

----
  
  blowfish.h     interface file for blowfish.cpp
  THE BLOWFISH ENCRYPTION ALGORITHM
  by Bruce Schneier
  Revised code--3/20/94
  Converted to C++ class 5/96, Jim Conger


----

*/
#ifndef BLOWFISH_H
#define BLOWFISH_H

#include <stdint.h>

#define MAXKEYBYTES   56    // 448 bits max
#define NPASS           16    // SBox passes

#define DWORD     uint32_t 
#define WORD      uint16_t
#define BYTE      uint8_t

class CBlowFish
{
private:
  DWORD     * PArray ;
  DWORD    (* SBoxes)[256];
  void     Blowfish_encipher (DWORD *xl, DWORD *xr) ;
  void     Blowfish_decipher (DWORD *xl, DWORD *xr) ;

public:
      CBlowFish () ;
      ~CBlowFish () ;
  void     Initialize (BYTE key[], int keybytes) ;
  DWORD    GetOutputLength (DWORD lInputLong) ;
  DWORD    Encode (BYTE * pInput, BYTE * pOutput, DWORD lSize) ;
  void    Decode (BYTE * pInput, BYTE * pOutput, DWORD lSize) ;

} ;

  // choose a byte order for your hardware
#define ORDER_DCBA  // chosing Intel in this case

#ifdef ORDER_DCBA    // DCBA - little endian - intel
  union aword {
    DWORD dword;
    BYTE byte [4];
    struct {
      unsigned int byte3:8;
      unsigned int byte2:8;
      unsigned int byte1:8;
      unsigned int byte0:8;
    } w;
  };
#endif

#ifdef ORDER_ABCD    // ABCD - big endian - motorola
  union aword {
    DWORD dword;
    BYTE byte [4];
    struct {
      unsigned int byte0:8;
      unsigned int byte1:8;
      unsigned int byte2:8;
      unsigned int byte3:8;
    } w;
  };
#endif

#ifdef ORDER_BADC    // BADC - vax
  union aword {
    DWORD dword;
    BYTE byte [4];
    struct {
      unsigned int byte1:8;
      unsigned int byte0:8;
      unsigned int byte3:8;
      unsigned int byte2:8;
    } w;
};

#endif

#endif

