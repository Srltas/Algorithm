(window.webpackJsonp=window.webpackJsonp||[]).push([[0],{35:function(e,t,a){e.exports=a(86)},41:function(e,t,a){},42:function(e,t,a){},50:function(e,t,a){},58:function(e,t,a){},59:function(e,t,a){},81:function(e,t,a){},84:function(e,t,a){},85:function(e,t,a){},86:function(e,t,a){"use strict";a.r(t);var n=a(0),r=a.n(n),c=a(15),l=a.n(c),o=(a(40),a(41),a(42),a(7)),s=a(87),u=a(88),i=a(89),m=a(90),p=a(91),d=a(92),E=a(93),f=a(10),h=a(8),g=(a(50),a(9)),b=Object(n.createContext)(),v=function(e,t){switch(t.type){case"login":return Object(g.a)({},e,{auth:t.auth});case"logout":return Object(g.a)({},e,{auth:null});case"getMyPosts":return Object(g.a)({},e,{posts:t.posts});case"getFriedns":return Object(g.a)({},e,{friends:t.friends});case"openPostModal":return Object(g.a)({},e,{postModalOpened:!0});case"togglePostModal":return Object(g.a)({},e,{postModalOpened:t.postModalOpened});default:return e}},w=function(e){var t=e.reducer,a=e.initialState,c=e.children;return r.a.createElement(b.Provider,{value:Object(n.useReducer)(t,a)},c)},N=function(){return Object(n.useContext)(b)},O=function(e){var t=Object(n.useState)(!1),a=Object(o.a)(t,2),c=a[0],l=a[1],g=N(),b=Object(o.a)(g,2),v=b[0].auth,w=b[1];return r.a.createElement(s.a,{fixed:"true",color:"blue",dark:!0,expand:"md",sticky:"top"},r.a.createElement(h.a,{to:"/"},r.a.createElement(u.a,{tag:"span",className:"mr-auto"},r.a.createElement("img",{src:"/img/bi-symbol-light.png",alt:"\ud504\ub85c\uadf8\ub798\uba38\uc2a4 \ub85c\uace0"}))),r.a.createElement(i.a,{onClick:function(){l(!c)},className:"mr-2"}),r.a.createElement(m.a,{isOpen:c,navbar:!0},r.a.createElement(p.a,{navbar:!0,className:"mr-auto"},r.a.createElement(d.a,null,r.a.createElement(E.a,{onClick:function(e){return w({type:"openPostModal"})}},r.a.createElement(f.g,{icon:f.e,size:"small"})," \uacf5\uc720\ud558\uae30")),r.a.createElement(d.a,null,r.a.createElement(h.a,{to:"/friends"},r.a.createElement(E.a,{tag:"span"},r.a.createElement(f.g,{icon:f.c,size:"small"})," \uce5c\uad6c")))),null!=v?r.a.createElement(p.a,{navbar:!0},r.a.createElement(d.a,null,r.a.createElement(E.a,{tag:"span"},(v.name,v.email))),r.a.createElement(d.a,null,r.a.createElement(E.a,{onClick:function(){return localStorage.removeItem("auth_token"),w({type:"logout"}),!1}},"\ub85c\uadf8\uc544\uc6c3"))):r.a.createElement(p.a,{navbar:!0},r.a.createElement(d.a,null,r.a.createElement(h.a,{to:"/login"},r.a.createElement(E.a,{tag:"span"},"\ub85c\uadf8\uc778"))),r.a.createElement(d.a,null,r.a.createElement(h.a,{to:"/signup"},r.a.createElement(E.a,{tag:"span"},"\ud68c\uc6d0\uac00\uc785"))))))};a(58);function j(e){return r.a.createElement("div",{className:"ui-box"},r.a.createElement("article",{className:"contents"},r.a.createElement("div",{className:"contents-accounts inline-items"},r.a.createElement("div",{className:"contents-date"},r.a.createElement("a",{className:"h6 contents-accounts-name"},e.username),r.a.createElement("div",{className:"published-date"},r.a.createElement("time",null,"3\ubd84"))),r.a.createElement("div",{className:"more"},r.a.createElement(f.g,{icon:f.b,size:"small"}),r.a.createElement("ul",{className:"featured-dropdown"},r.a.createElement("li",null,r.a.createElement("a",{href:"#"},"\uac8c\uc2dc\ubb3c \uc218\uc815")),r.a.createElement("li",null,r.a.createElement("a",{href:"#"},"\uac8c\uc2dc\ubb3c \uc0ad\uc81c"))))),r.a.createElement("p",null,e.contents),r.a.createElement("div",{className:"contents-info inline-items"},r.a.createElement("a",{href:""},r.a.createElement("div",{className:"contents-icon inline-items"},r.a.createElement(f.g,{icon:f.f,size:"small"}),r.a.createElement("span",null,e.likes,"\uac1c"))),r.a.createElement("a",{href:""},r.a.createElement("div",{className:"reply"},r.a.createElement(f.g,{icon:f.a,size:"small"}),r.a.createElement("span",null,e.comments,"\uac1c"))))))}a(59);var y=a(14),k=a.n(y),x=a(18),C=a.n(x),S=function(){return localStorage.getItem("auth_token")},M=function(){var e=localStorage.getItem("auth_token");return e?C()(e):null},P="/api";function q(e){var t=e.email,a=e.passwd;return k.a.post("".concat(P,"/auth"),{principal:t,credentials:a}).then(function(e){return e.data}).then(function(e){if(!e.success)throw new Error;return e.response})}function I(e){return k.a.get("".concat(P,"/user/").concat(e,"/post/list"),T()).then(function(e){return e.data}).then(function(e){if(!e.success)throw new Error(e.error.message);return e.response})}function T(){return S()?{headers:{api_key:"Bearer "+S()}}:{}}k.a.defaults.headers.post["Content-Type"]="application/json";var _=function(e){var t=e.friendId,a=N(),c=Object(o.a)(a,2),l=c[0],s=l.posts,u=l.auth,i=c[1],m=Object(n.useState)(null),p=Object(o.a)(m,2),d=p[0],E=p[1],f=function(e){E(null),i({type:"getMyPosts",posts:e})},g=function(e){console.error(e),404===e.response.status&&E("\uc0ac\uc6a9\uc790\ub97c \ucc3e\uc744 \uc218 \uc5c6\uc2b5\ub2c8\ub2e4.")};return Object(n.useEffect)(function(){(t||u)&&I(t||u.userKey).then(f).catch(g)},[t]),r.a.createElement("div",{className:"row"},d?r.a.createElement("div",{className:"col-12 text-center mt-5"},r.a.createElement("h1",null,d)," "):t?s.map(function(e){return console.log(e),r.a.createElement(j,Object.assign({key:e.seq},e,{username:e.writer.name?e.writer.name:e.writer.email.address}))}):u?s.map(function(e){return r.a.createElement(j,Object.assign({key:e.seq},e,{username:u.name?u.name:u.email}))}):r.a.createElement(h.b,{to:"/login",noThrow:!0}))},z=a(94),F=(a(81),function(e){return r.a.createElement("li",null,r.a.createElement("div",{className:"user-thumb"}),r.a.createElement("div",{className:"noti-text"},r.a.createElement(h.a,{to:"./".concat(e.seq),className:"h6 noti-account"},e.name?e.name:e.email.address)),r.a.createElement("span",{className:"noti-icon"},r.a.createElement(z.a,{color:"danger"},r.a.createElement(f.g,{icon:f.d,size:"small"})," \uce5c\uad6c \uc81c\uac70")))}),B=function(){var e=N(),t=Object(o.a)(e,2),a=t[0].friends,c=t[1];return Object(n.useEffect)(function(){k.a.get("".concat(P,"/user/connections"),T()).then(function(e){return e.data}).then(function(e){if(!e.success)throw new Error(e.error.message);return e.response}).then(function(e){c({type:"getFriedns",friends:e})})},[a.length]),r.a.createElement("div",{className:"row"},r.a.createElement("div",{className:"ui-box"},r.a.createElement("div",{className:"dropdown-title"},r.a.createElement("h6",{className:"title"},"\uce5c\uad6c\ubaa9\ub85d")),r.a.createElement("ul",{className:"noti-list friend-requests"},a.length>0?a.map(function(e){return r.a.createElement(F,Object.assign({key:e.seq},e))}):r.a.createElement("li",null,"\uce5c\uad6c\uac00 \uc5c6\uc2b5\ub2c8\ub2e4."))))},J=a(21),K=a.n(J),R=a(34),W=(a(84),function(){var e=r.a.useState(""),t=Object(o.a)(e,2),a=t[0],n=t[1],c=r.a.useState(""),l=Object(o.a)(c,2),s=l[0],u=l[1],i=N(),m=Object(o.a)(i,2),p=m[0].auth,d=m[1];if(p)return r.a.createElement(h.b,{to:"/",noThrow:!0});var E=function(){var e=Object(R.a)(K.a.mark(function e(){var t,n;return K.a.wrap(function(e){for(;;)switch(e.prev=e.next){case 0:return e.prev=0,e.next=3,q({email:a,passwd:s});case 3:t=e.sent,n=C()(t.apiToken),localStorage.setItem("auth_token",t.apiToken),d({type:"login",auth:n}),e.next=12;break;case 9:e.prev=9,e.t0=e.catch(0),alert(e.t0);case 12:return e.abrupt("return",!1);case 13:case"end":return e.stop()}},e,null,[[0,9]])}));return function(){return e.apply(this,arguments)}}();return r.a.createElement(r.a.Fragment,null,r.a.createElement("h1",{className:"text-center login-title"},"\uc6f9 \ud2b8\ub799 \ub85c\uadf8\uc778"),r.a.createElement("div",{className:"account-wall"},r.a.createElement("form",{className:"form-signin"},r.a.createElement("input",{type:"email",value:a,onChange:function(e){n(e.currentTarget.value)},className:"form-control",placeholder:"Email",required:!0,autoFocus:!0}),r.a.createElement("input",{type:"password",value:s,onChange:function(e){u(e.currentTarget.value)},className:"form-control",placeholder:"Password",required:!0}),r.a.createElement("button",{onClick:function(e){return E(),!1},className:"btn btn-lg btn-primary btn-block",type:"button"},"\ub85c\uadf8\uc778"))),r.a.createElement("p",{className:"text-help text-center"},"\uacc4\uc815\uc774 \ud544\uc694\ud558\uc2e0\uac00\uc694?"," ",r.a.createElement(h.a,{to:"/signup",className:"text-center new-account"},"\uacc4\uc815 \ub9cc\ub4e4\uae30")))}),Y=(a(85),function(){var e=Object(n.useState)(""),t=Object(o.a)(e,2),a=t[0],c=t[1],l=Object(n.useState)(""),s=Object(o.a)(l,2),u=s[0],i=s[1],m=Object(n.useState)(""),p=Object(o.a)(m,2),d=p[0],E=p[1],f=Object(n.useState)(""),g=Object(o.a)(f,2),b=g[0],v=g[1];return r.a.createElement(r.a.Fragment,null,r.a.createElement("h1",{className:"text-center signup-title"},"\uacc4\uc815 \ub9cc\ub4e4\uae30"),r.a.createElement("div",{className:"account-wall"},r.a.createElement("form",{className:"form-signup"},r.a.createElement("div",{className:"form-group"},r.a.createElement("input",{type:"text",className:"form-input",name:"email",id:"email",placeholder:"Your Email",value:a,onChange:function(e){return c(e.target.value)}})),r.a.createElement("div",{className:"form-group"},r.a.createElement("input",{type:"text",className:"form-input",name:"name",id:"name",placeholder:"Your Name",value:u,onChange:function(e){return i(e.target.value)}})),r.a.createElement("div",{className:"form-group"},r.a.createElement("input",{type:"password",className:"form-input",name:"password",id:"password",placeholder:"Password",value:d,onChange:function(e){return E(e.target.value)}})),r.a.createElement("div",{className:"form-group"},r.a.createElement("input",{type:"password",className:"form-input",name:"re_password",id:"re_password",placeholder:"Repeat your password",value:b,onChange:function(e){return v(e.target.value)}})),r.a.createElement("button",{className:"btn btn-lg btn-primary btn-block",type:"button",onClick:function(e){return d!==b&&alert("\ud328\uc2a4\uc6cc\ub4dc\uac00 \ub3d9\uc77c\ud558\uc9c0 \uc54a\uc2b5\ub2c8\ub2e4."),function(e){return k.a.post("".concat(P,"/user/exists"),{email:e}).then(function(e){return e.data}).then(function(e){if(!e.success)throw new Error;return e.response})}(a).then(function(e){return function(e,t,a){return k.a.post("".concat(P,"/user/join"),{principal:e,name:t,credentials:a}).then(function(e){return e.data}).then(function(e){if(!e.success)throw new Error;return e.response})}(a,u,d)}).then(function(e){alert("".concat(a,"\ub85c \uac00\uc785\uc774 \uc644\ub8cc\ub418\uc5c8\uc2b5\ub2c8\ub2e4.")),Object(h.d)("/login")}).catch(function(e){e.response&&alert(e.response.data.message),console.dir(e)}),!1}},"\uac00\uc785\ud558\uae30"))),r.a.createElement("p",{className:"text-help text-center"},"\uc774\ubbf8 \uacc4\uc815\uc774 \uc788\uc73c\uc2e0\uac00\uc694?"," ",r.a.createElement(h.a,{to:"/login",className:"text-center login-here"},"\ub85c\uadf8\uc778 \ud558\uae30")))}),$=a(98),A=a(95),D=a(96),G=a(97);var H=function(){var e=Object(n.useState)(""),t=Object(o.a)(e,2),a=t[0],c=t[1],l=N(),s=Object(o.a)(l,2),u=s[0],i=u.postModalOpened,m=u.auth,p=s[1],d=function(){p({type:"togglePostModal",postModalOpened:!i})};return r.a.createElement($.a,{isOpen:i,toggle:d,className:"post-modal"},r.a.createElement(A.a,{toggle:d},"\uac8c\uc2dc\ubb3c \uc791\uc131"),r.a.createElement(D.a,null,r.a.createElement("form",null,r.a.createElement("textarea",{className:"form-control input-lg",autoFocus:!0,placeholder:"\ubb34\uc2a8 \uc0dd\uac01\uc744 \ud558\uace0 \uacc4\uc2e0\uac00\uc694?",spellCheck:!1,value:a,onChange:function(e){return c(e.target.value)}}))),r.a.createElement(G.a,null,r.a.createElement(z.a,{color:"primary",onClick:function(e){a.length>0&&function(e){return k.a.post("".concat(P,"/post"),{contents:e},T()).then(function(e){return e.data}).then(function(e){if(!e.success)throw new Error;return e.response})}(a).then(function(e){return I(m.userKey)}).then(function(e){p({type:"getMyPosts",posts:e}),c(""),d()}).catch(function(e){e.response&&alert(e.response.data.message),console.dir(e)})}},"\uacf5\uc720\ud558\uae30")))},L=function(){var e={auth:M(),posts:[],friends:[],postModalOpened:!1};return r.a.createElement(w,{initialState:e,reducer:v},r.a.createElement(O,null),r.a.createElement("div",{className:"container"},r.a.createElement(h.c,null,r.a.createElement(_,{path:"/"}),r.a.createElement(B,{path:"/friends"}),r.a.createElement(_,{path:"/friends/:friendId"}),r.a.createElement(W,{path:"/login"}),r.a.createElement(Y,{path:"/signup"}))),r.a.createElement(H,null))};Boolean("localhost"===window.location.hostname||"[::1]"===window.location.hostname||window.location.hostname.match(/^127(?:\.(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}$/));l.a.render(r.a.createElement(L,null),document.getElementById("root")),"serviceWorker"in navigator&&navigator.serviceWorker.ready.then(function(e){e.unregister()})}},[[35,1,2]]]);
//# sourceMappingURL=main.750b0039.chunk.js.map