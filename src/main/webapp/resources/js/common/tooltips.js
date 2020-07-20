var btns=document.querySelectorAll('.tooltipMsg');for(var i=0;i<btns.length;i++){btns[i].addEventListener('mouseleave',clearTooltip);btns[i].addEventListener('blur',clearTooltip);}
function clearTooltip(e){e.currentTarget.setAttribute('class','tooltipMsg');e.currentTarget.removeAttribute('aria-label');}
function showTooltip(elem,msg){elem.setAttribute('class','tooltipMsg tooltipped tooltipped-s');elem.setAttribute('aria-label',msg);}
function fallbackMessage(action){var actionMsg='';var actionKey=(action==='cut'?'X':'C');if(/iPhone|iPad/i.test(navigator.userAgent)){actionMsg='不支援一鍵複製 :(';}
else if(/Mac/i.test(navigator.userAgent)){actionMsg='Press ⌘-'+ actionKey+' to '+ action;}
else{actionMsg='Press Ctrl-'+ actionKey+' to '+ action;}
return actionMsg;}