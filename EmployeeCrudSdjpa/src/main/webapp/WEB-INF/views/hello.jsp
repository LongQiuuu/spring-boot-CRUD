<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel='stylesheet' href="<c:url value='/css/styles.css' />"  type="text/css" />
</head>
<body>
<div align='center'>
   <h2>${helloMessage}</h2>
   <h3>${welcome}</h3>
   <hr>
   
   <img width='60' height='80' src="<c:url value='/images/kitty.jpg' />" />
   <hr>
   <img width='120'  height='180' src='data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAoHCBYWFRgWFhUZGRgaGBgaGhgYGhkcGBoaGBgZGhgaGhgcIS4lHB4rIRgYJjgmKy8xNTU1GiQ7QDszPy40NTEBDAwMEA8QHxISHjQrJSs0NDQ0NDQ0NDQ0NDQxNDQxNDQ0NDQ0NDQ0NDQ0MTQ0NDQ0NDQ0NDE0NDQ0NDQ0NDQ0P//AABEIARMAtwMBIgACEQEDEQH/xAAcAAAABwEBAAAAAAAAAAAAAAAAAgMEBQYHAQj/xAA8EAACAQIEAwYDBQgCAwEBAAABAgADEQQSITEFQVEGImFxgZETobEHFDJCwSNSYnKCstHwovEVkuHCM//EABoBAAIDAQEAAAAAAAAAAAAAAAEEAAIDBQb/xAAmEQACAgICAgMAAgMBAAAAAAAAAQIRAyESMQRBIjJRE2FxgcEz/9oADAMBAAIRAxEAPwC1mFZYaGUSgBMJDWhaj2naT5pCAVoe0MqTtRwu8hAoEMFjKpUN9NosmKXYmCy3FivxwDaLOe7K7VZmrCx0ljCWT0kTsko1Rmfaw9+V5RLB2r//AKSCtIYy7BOCdgkM2CGMKIeQASFaKGFaQshIidUTpE4BIEDGdUwrCdK6SAoPbWCcSCQJsxsN4YLK1xfFuKqqt7X1lkw2ii8idjEo0kxrjwcpsIjwhGsc0kqi3E5RTSGtgvVDZ8WA2WRXGXfMtuZj/wC5k1M0X4hhbgaSrTaLRaTRUe0nac0FFOkAXI1Y6hfTrKBjcfWqPmd2uehIHsI64y7feKituGhWzaXWxt0llSQat6HPC+KPhznD3PQ6gzSeA9qExKEWyuo1X9RMgxFF73ym3kY+7L4spiF13095H1oFb2T/AGnqXqSCNYS3YvhAqFnckKDoFtcnzMajgKMRZDbqT/gyuvYP4nJlbNWD4ks2J7NhdQLgb6yPfgnduhzEalTvbw6wpplJYXHbRGo0UUzqpblDWkMGwjL4QpWOPvCkWtCJXA3kCho7WgzResAdRCBJC6aoSMBqiLtTFo2alIFUxVKkEPTTSCEnxNaxSKt3I1Amd8W7V1jUIU2VTa00rE0c6kTP+NcByOXt3TvKt0bwXJ0WTsrxk1k72nnLPSI6zM+F4hQ4RGtLhWpOiZ0a5AuR1kjKymVOL1snsms7UWVvhfaNXNm0PQywtiVK3Bl3rszhOMujKe0/D82MfKtiQPXTeHwGAxLvkUjQX1ANgJYO0dBS5qKddjbeReF4i1FwyWzHQljF5Td0dLHBVZ3B8MrsWD1ABY329p3hfZ5RVZwL5RZWI/Mb3I8h9Y/xCZhnDAsdWC7Ax5Wxnw6aqv4rE+ep5+krjk3IOWKSGa4V61XIcwSnaw6nykxisMwAUDztzPIfSM+CYxdRm1B9zzMs+FrqLFgLXG9uskpXIMY1HRE18K2RTrYgaW5jfykFXwjK2dN7bDw2NttpoFeojaAAjwt+kYGmg0FtZHKiKNrZlnGcOFAqp+FjqLHRv9BkO2JMvvGsKgp4hBbSzr4XsfmQZm1eoVNoxH5Kzm5YKM6HNN7mHrKYjSc72hXxJvaWUTGT4vQ9wtEtpFsRg3TW2kTwNQjW8k3xt1sZZxQussnLrQ34RgzWJF9RGuPwr03KMJzCY9qdXMm50I6yYqN94dc4ykDf/uZU+Q83H+O62V/4hEEdcfwvwra3B2M7L0ZLezYVOsje0yD4TG3KSSCIcVoZ6ZHhKsY2ujDjVYNcEgg/rNC7L8eNRAjnvAW15iUfF0QlZkba8P8AfPhsChsYWr6MVJptssfHaAR8yNo2unIzuD4+yjKzXkPhhWxLhUVnc7KPr4DxlowP2c4jLnrOqDfKO83qdhL2qpikYSc3KAvSofEUsTpa8gKioWOoNjvHfarGJQQUKLlmP42vsJAcLq90A8usXnClaOx4+ZyfGRZBi10VL+0R4rime9tMoy+ZW19fX5TlJ7bCGqUybsP3WFv4jz9h72mMPi7G5pSVew3BMM9g9vG/L/uWehxhEOWorpfY5r381B095HnCP8FRT0CgXN/mZWcdw+sjXYX1uW+IxJ9Pw2k+zLVSpGjDF0msQ+YAa5lzaaba3+ZgxHEKCiwqrmttdj8gdJTaFGv93NW34TbfTlrI3CcNxDvnUG2+cPppytt/3Ald2RpLoleKVWtX5lgieNmN9PS/vK1RwSk3M0Hh+GQuUc99kRiDbkMv6SF7ZcFal+1QdzZrcj1jmJpRSOH5sZSm5RfWivtRU6ARLGcKyqGERw2JN9JNYPC1ao0GkvKkrMPGc2+NWQNERwcPpcGO6vDyhs4tEcT3RpCt7BNuDcUti3Cgit3iL+M7xfEWIKHbmJAfeGzS04bgr16OdiVFtB185SSjF2bQzZZVFJV7KfxLiLvYMb2ghcVgirMN7G0EhtyitG7YfEZt9POODqDIrh1bOma9lt6xxhcaudlvsNpm2ax2jMe2WHUYleVzqZHcRRAotvJ7t0mepmVdhqfWVTAU89RAx0zC/lfWXi7VmWSPF0/ZqXA8XT4bhEdkzV6wzAaX2uN9gLiQHF+1uJrowd8q/uoANPPeRnHuI/FqZh+FLIo6ARiilu4oJZjYAC5JOwAG5l4x9sTyZHfGPQ2dkc/iAPQyX4P2fr1ULo6AA/m5SZ4F9lleqc2Ib4Kb5RZqp9Nk9ST4TSeF9n8HgkAAVRuXqvck9e8bX8hBJroYxRcXyWjNcP2PxJTNnZzyFNGZfMvt7SJ4ji3wwemytnAvZlK2PXXU7TXOLdsMJTRr11JtbuZn180BExLtPikq13dGzIwFj5XFoEk9NFpykpKSltF0wXEWS2U6EA2OuhA/zO4j4LOLL32YCxYhBfmf3RK/wWrnpISdQoUn+Xu/pFa2M+GCHS6sLFrZrG99eetukRafJo7cJJwUi/4WvTVHRmp7WFjdNBpe+tusreDbDq5/ZjOrEXViyHXRlBOx3Er1PFUbdwhifyq4JJOwyD03AjrE8QFBkcqAxdTl3sisC22l7fWThJuisskYxcmWHinBsaKq4hKbHRbAamw1sQL9edpP0sQtekUqoVJFmRxZh6GSHD+2GDqWtiEUkDSpdPm4Ak0GSoPyuvow9xG+kl+HJq5OSd2YziODLQqFQbpup/SPOF8ZSi+VhoZcu0vZMVFL4ckOPyE91uuUnY+Zt5TKMSDnIYEEGxB0II3BHI3mjSlGhSLngzcq0yy9pK61LMgkxwGthqlMI+Qm1irWvK/gMKHp/j15CNqnB3Rg50AO4MCikqReWdynylHRO43stQzgpoCw7t/GSHbXEpQwyouhayi3TnGPZhWeuiliQATr7CJ/azRZRT/dv87SlPkkzf4cHKBnuJJU5l1vBD0V070E3oQ5pGv8UwjBCKWnjILg3B6quS7XN95eQkOqDpEm2zsqKTspXaXh/wCzJA1tMz4eMrknlf3m68VwudCoG+nvMx7WcNXDmmijdTc9Te5+s0wvdGPl7jyRW2NmIPPWad2coUeG4ZcTWTNXrfgX8wU6hVv+EWsWO+oGuglR7J8E+84qmGHcT9pU6FUIIX+o2HleDtZxhq2JYk91XKIOSqjEaDxIJ9Yw9uhCL4x5e/RM8W7aYmtcBxTQ/lTQ26F/xe1pWHe5udT1Op94mXhXMKSQtKU5O2zuJ7ykeHzEgQN5O3sZCFLNbxI/xCaYumh7wbiPwns34GOvgbW/xLggDKDcFSNjKBUoxWhisQtkQtbwF/cG4i2XDydo63i+VxjxkX/D0FGtlFufQSn8VxorVWYfhXup5C+vqbn2jHE43EP3HqEg7oCv/LL9IvgKQLop2Jhw4eLtsp5vlKceMVokkGg8hDIxU5lJUjmNCPUTlXQkeOv+YmTGDjq+0WngvbbE0XAdzVTmr6tb+F7Zr+d4j9oOIo1kTG0dA5+HVQ/iWoFuuYC4uVB1vrlErSPrHnZ+j95GIwxP46ZZP56Tq4/45x6mUkktjeGcpPjLaIfCcYqJosuOG7SK9Ih/xWsRI/BdinB1JHtH2J7GuiFgx0G2kzU4v2N5cUq6H3YDitNK7Z2AuABfwMU+1HiKV/hpTYNYkm2tukz9+6xF+douKltbzTim7E3NxjxijuEocmMEbNUN7zktYu8cm7s3zPAtYdYyqU2O0FPCtED0FEmGFr9Jkv2gcRz4lV5KtvUn/wCS39p+Mvhk0B6e8zzC4R8VXRd2qOL+FzqfIC59JviXsU8mWuP6XbgtQYLhr1zYVKo7l9/zKmnQd5vKZtiW7yHr85fftMqhBSoJoiIxt4BVVPkGmfVGuKR6Nb32/WbR2rFMipqK6Q/blF1S49InlupimHbS0uJy6Ey20i8fTs58dfeSrrGePHe/pH6yGmN0yMzt1gDNzt6CLlJ100gaGYy2N8pG3z1McUmIZdFJvzFx7QIm3kPpDot2HmPrIkVlIlEHLlaEvY267RTnEq6XH+6eMIsuwmxjvsriPh46k3L4oU+ThkPyaNACQD46xuamSsp6OG9gplZdG2J1I38oBIbjfGURGBIvY6SfrMLecpva3hqvTZlW7W5bxNUns68uTi2jKcY12J6kn3MafeSNI4rU2BIYEHod4itLWOnLqtMBqkwRQLOSUS0b+GiitG6GLLEDsDXi3DVrIVIvcSudi+BfCxNVibin3FP8Tg3HmF/vEtlXGIm5EynEdpK1HE1XpPYPUdih1Q3Y2062trNsabuhTPKKkm/0f/aU98W46UkH/tmP6iUfN3B4Ov1t+sleNcUeu5quBmcLoNtFAFvaQrHueo+t4xFUqEm+Um/7JzDNf1hFGUzmGOn++0NXXnLCr+zQq40vGONGo8h+sfU20jbGroD6SEi6YwM6NoGnDsZBiPoMu14rh6ZJB5XGvlYxC/d9BLT2VwdH7vXxNemai0cgFPMVDGq5Fyw10A2kI422RRfrOpUUb6jnbeXapwajh2xlUUkqpTSi1JKgzL+31BYfmy6gTi0KFLiVD9jday0HpoGslNqgAJtY5gCCQDbUwFXi/ShU3sSN7fNd7+Y/zGWMb9qPIH3/AOhJPiNPLWcDk7j2cj9JG8RTLV8MqkeF/wDflIGC+TRu/BMSauGpu27IPkMv6R2mFXnIPsFXz4RQd6bsnpZWH90sirE5R+TR1cUrgmZB9oWEC1gVFhbX3lON5on2j0e+plLXC3WMY38RTO0pDNDBCVbqYJcwPQaJDsLA2hQYdTETrmY9q8TV+NY3CAE35GU3EPc2mq/aDTUYa9tWdF+rH+2ZfiaAXURvE7icvyKWUb4iobKu4y+voYnVSy73uRBimtlNuRH++8SJ7pmhRImsMdIo40MbYGqGUEf6Y8qyIWnakJ0WijpmUj/bxBdDFw1oSrWyKqoQbEawh2Mk8fTBW/MSMgZvjlYFU2A8BLV2W4hSWniaFdmRKwUZ1XMUdDmQ5BqRqf8AdoOnYqPIRD7qpa5FwYQ8tsvGI7WYdqtdXDNhqtKnSsrKKgFIDI1jpcnNp4iQnEe06ti0xCKctL4YpobnuUrZQzDmSCT5yKGDTpDLQQflkA8i9hsTW+IzVLWzu7W6ZmJI9zGHFqeaonTIv9xEkTbQDQdI2xQvWpjoBf8A9iRIUhLd/wCTU/s6dVoVA3JwdehRR/8AmTGO7S0adwXW/QG5mXPiKqU2yEhbjMoJBP8AF5XNpCVsW55H5zGUVdsaw5pOCjFF47cVhURXG1xb1lRwxuJY+I1s+EQEa2WVfDNle0kOieQ3y/0MeKJYzkdcZpG4Njbry94JewRuujdFhswhFENkuIkdYpv2kYoFKKA/nZz6AKP7jKBi17pPhLN27cfegnJKae7FmPyyyt4tSFbpb9Y5jVRRxvJleZkXW1Qn90g+h0P6e0ZO/dNo/oEfhOxBB8iLSNr0itgep+RtLMvBIfcGqWJHLeTlbQAyv4EWF+kn6Lh08oUYZl8rETqL2MPTN9IghsbRQ903hM2hxSIIymRT0ypYdP8AMlPGdxFPMD6cvEQMOOXGQzoLYW8B9IqId6PjyH0EKBCRu9nUMMTpCNOK8gGg2bWR2NxDCqxXcEC9rkWAGnSP2ew/3SF4c99bNlJJLXsTffykLR+KcqH+CxT1EF1Kuuma2VWHI66X/wAzUOEcNpVaNNyi3ZATYaX2JHgbX9ZlVPDMcTSpLny1WVTmNxqbEqedhc+k3CjSVFVFFlUBVA5ACwHtMMz0kO+FBpuS6fopXbfColMZdNR9ZnuJpkG4E0L7RRZFP8Uo9DEBrL1IHzhx/Up5NrJaLo/Bqb4FCRyU+txBJzidG2DUD+H6iCLt7OjCKSJEGKrE7TtSqEUsdlBY+QFzKouzKu2D3x1VuWZVH9KKD87yOZNCDqpEbcSxDVXaoTqzM1vMk/rHGDxOljH4qlRwc7blyRD16RU2Pv1iHEiDkPUG/mLSaxXFU2yh/MaSCxdXO1woUcgNhIzXE5PbQbCDQyWwFS3PSReF2i9JgDIGceSZIugJNod00Bia1BbTedp1CdCYRemCmYs72B9PqIVqdj4ROvt7fUQMkackLM9wPIf2iJgziNoPJfoJ28JKCssIxtDMY3qHoZCyViioWBAGltempA/WcSugpLnUlrbbbxbh1WxIP7p+oP6RhhaZNVydlYge+nygLVaafosHZ7iK0qiOrAqGDKrHMym1iDcaXGnhNmRwwDKQQRcEbEHYzz9xHh9hnW+4zAePSbb2fwrU8NQpsLMlJAR0OUXHvMM3od8J909EP2+o5qJPQgzLqLgOPP6Ga92tt8B79DMgezG40POTE7VE8qNSTNPxePZ8EpXfu/UXnJTKvEqlPDBQLrcWPrOSrjs3hkfE1wLILtpjhSwdQk2L2pj+s2P/ABzR9wbiyVVGve6SH+0rA58EzDem6P6XKN7ByfSUjGpKy8pqcG4mT/8AkADopMPSxDN+GmT5bf4iWHRQM7KW/dUaA9Sx6eEcJxVtlVQBpa3y0jhy5L8Q4qVkAAq0it9jYWPqDISsvea219ADcActZLYvGVMvfRWUkb8jy56SKZdTbzkJjVK/+2K0PwxSJ020h7yMsdQmPFfTb0iFARVZCkhVH01OkPVa49vqIituc7UbT2+okZVLaHCnQeS/2iHCXiV9B5D+0RrUzjXlCV42xxWSNCsUp4kkazhOsBeKa0FRwCfKaNw7sZSIDvnT9nTzhTr8XLdySwPVRYWsQYt2L7IoiLiaoFR3QPTQju0xa6kg7udN9B85M4fG1BSQ2XOy5ufeJ1N+mpOsWyZN0jpeP4yabkhjiOzNOnkeiWVkdGuxLhgrAkFTzI2tbWSNXjoG6kekUw3Ec4OZcthqpsbMOVx6xaphUdb6EHmNpk5N9sa/ijH6qiv8W4sKiMLcpVOBdjq2JqEnuU7721PlLljOzoJupI8pO8HxBoqFK7c5pjkkZZcfIqfajgfw6aUuWmvlOy747FU6gsReCRtX2RJ10Z9wvgGK0dAF/mJv7AS1U+H13ptTrZWDqyNa+zCx3jfhPbKmVCujUztYqf8AEmafGab7OJdzUuzDF438fVmG9pOC1cG4SoNx3HX8LAdDyPURlwqnmN7FmJNlUFjfW+gnobiC4etTy1VSop3VlDL52PPxlUxeFwyV0+70adMBGzFEVfxNZSbDW1m9zC8lIi8ZTddIp6dl8S6H9g2Urm1amL21Gma99NpVsZhiDnA/mGxB56TdqLMFWx6b7eNv95zMe3eBWnUdrWWopcAfvk2I/wDbX1MEMjk9lc3irFFOH+ymil0MWo0L76AbmR6Ow2Nx0MWGJ5E2+k3sWcH6Y+rVRsu0T+OdokhB5xZUA1J9JCtJAVyeUM7G3+/7ynGqiJvV2EDZIxbY9qKbaRqarRfD4sDeOXx9HcoD5iEpck+iLDyR4Jgvj16dIsEFRwpZiBpzAv8Amte3jaMcTi1J7qBfHX6RspYsCCbjUHoRtbpA1ZrB+2j0fXGVGC6WQhR0spAHylaTjIwuHDViM/w+4l8ufKuYqG1CnUb8zJbgnEfj4alV3LoC38w7rj3BjDjvCviKqpSpugDfs3sig8nUhbq24v0iMu9nXxtONfpF4riC11atSJyuiAC4uHsbq4F9RcA+Un+C4bJRUE6kBvAXAsAOQtaVfCdnMTnTMUSmNMisTZbWsoAt85cs3SVS9msmqSQYmcLTmaFzQ2ZAKwThaCTRDjYVDui+wiZwKcltF1YTtxDSBbEEwIGxMhOL5VrNcqoVKYF+ffYnXpa4lkNusi+N8Kp4lMrHK4HdcbjwI5r4SUWg6eyO47xQCghRwt3RGNtdWUEAi9jK79oTk0kD6kZSDa2+hHraLLw9/h1cO5OdCrhQws2VlcZb8mCxt26pMyUiXUq7Bl5kqAfpmX3hx/ZB8hpYmygCnA1M+Y+cmsPdNDYjlDuQ2mVQfER44H8rTK6yDpadXMNjeS2Iwdvy+20Y5B0ko1jO0JfEbpCgG94sqnwnADe2nnBxLKWgoczhBMP8I9fadpqQbaw0VchSlhS2pIAj+jgVtqx9Bb6xXDUQACR5R1lJ8IRaeRvovX2d1R93ele+R7i++V9f7g3vLSRM77F4o08Sqnaoppnz0ZD7i3rNEqRPNGpHX8PJzxr+jhaEzTt4W0xGzpiK4qiD36gXwuBFGewlH7QYF85ZFY3hTVkqy/0KNKp+CoD6gwTL8G1ZDezr5Xglrj+A4v8ATRypgEKj+EWBEBAthOoi3vzh/h+MCJ1kIZ59ppdHpujFSwKsRubba+8rL1icPQuSTnrnXXnTA/tPvNE+0ThwqYbMu6HN6c5mtcFaWHFiTaqbDxqsPoIxiSpCHkuVtX6FEQHqDHAOljr4xrTqMfwgN1U91h77xWliVJylWVv3TbXyvvGDmOLYqATpuJx+HJuSR5ERJ+KqvdCFTzDaGN3x5MlgUZ+tBMXh1XVSSPHl6iMKn4pLrSqNSqVETMiWDt0zbC3M85CNVvvpA5IZhGVbJDBun59eg2HrJeniqf7qjyAlYzQB5EyssSfstBWm2xt5GEZ0TmT5SFw+YakkA7DrHq4tBYk3PJRqYbMXia/sm+A11GJos/dX4im3MW2LdBe15rbCYnRxIJ1UqSNMxH05TaMNWDojjZkUj1Ai+ZdM6HgSpOLAwEKUEM9oTu9YudEIVhMl9xFCB1nCPGQNiTU16D2gnWMEATi2M7kJjcOYyr8dRCVbNceEi2QnQNIoFlXPaheSH1nU7SNuE9zCVFu3TsuDcr018ucyv4hKIWOlmy+QdgfneaTxPi5rUXplAQykbyh8Sw+RMMjIVb4Tkqd9a9Sx9QAYxhfoS8uNrkNRbe+vgIozqwytfwJH68onTPSOE1FjaMHMloKtEFcrZXXkQe8PXeMsVw9VFw/kD/u8UxmKVNFtfwkr2CwPxsSKlUjJS79jsX/IPGx739IlZtRVm2HHKclTqzQeE9mQnD/uzWz1FLuf42A09AAPSY7xbhb0nZHWzqbeDDkR/megkrKecp3bjs/UrH4yOvcU9wjf1i0J/LZ0cuJ8ddoyFF5Rem4XW1zyvt5xbEOr65bONx18POKUKdNrdfAxpMRk9W0MyWdtT/8AJJYagqi41PWLJTAFlsIUmExlO9IXTqQP1mndjMTnwwW+tNmT0/Ev91vSZdTMu3YHFEVHQ7OmYeaED6N8pnlVxL+LPjlS/dF1eImOGPhE8/hEztISa/OFz3izMIkSOUgRJm6QTpggCJZL87SDxvAb3ZXu38UnWSAIDygVomippwasOS+YMLicHVpgEi4/h1tLhlUcocjTQAy3JkpDHs/2Zd7PWFl0IQbn+b/EpH2jOP8AyDgaBFRAP6Fb6sZpmA7QVB3amHcW0zDKwPlY3+UyTtQ7viazVQA5fNlGuVToinxCgA+IMaxOPo5/lWlsjwpB29oZ3toISnUNrQ66TY5zEHwgOi3zE7b3J2Et3DMMKNMIN92PVjv6aAek52BwaVK7u4v8NBlB2zOSL+gBHrLzV4TRP5LeV4tllvidPxMbUeT9lPTHlGuDt5yfwPHw6sr5Rp5X94s/Z+gf3veNK3ZWk2zOPWY2hwe9kOFYVGZ1UF3JJz2Nrknu9BKD9p3Bhh8aWQBUrL8QAaANs9hy11/ql54Xw5qD3z50tzHeHtG32h0UxOFDAj4tEl1B3ZCAHUegB81EYhki0L5cb9GRpiSPGPaNdW85GEW8o6Skp1U6/ObI584okEOsm+z+L+FXpvfQMA38rd0/I/KVtHtzvHuGq9ZGrVGG4yUl6NpcRG0i+zXEmr0Sz2zq2UkaZtAQbddflJR2G14jJcXTO5jkpRUl7OWnDCC/KCx6wWaHWtBCE+MElhDCCCCQATkYanBBKssPMJ+JfP8AzMk7Zuf/ACVYX00Fv6QfqT7zkEZwCPl/UjBvOvBBGGctF5+zJB8Ouba/FUX8AoNvmfeXRoIInk+zO1g+iEzCwQTM2OxZaY6QQQgMNr0lFeqttA7gDoAx0jB0Fzpzggjsejkz+7DUjrHqwQS5lLsv/wBnf4Kv8yfRpaXggiWX7M6ni/8AmhNt4VzBBMmMhKkEEEAT/9k='  />
   
   <hr>
   <a href=" <c:url value='/' />">回首頁</a>
   
</div>
</body>
</html>