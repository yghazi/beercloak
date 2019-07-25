<#macro mainLayout active bodyClass>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="robots" content="noindex, nofollow">

    <title>${msg("accountManagementTitle")}</title>
    <link rel="icon" href="${url.resourcesPath}/img/favicon.ico">
    <#if properties.styles?has_content>
        <#list properties.styles?split(' ') as style>
            <link href="${url.resourcesPath}/${style}" rel="stylesheet" />
        </#list>
    </#if>
    <#if properties.scripts?has_content>
        <#list properties.scripts?split(' ') as script>
            <script type="text/javascript" src="${url.resourcesPath}/${script}"></script>
        </#list>
    </#if>
</head>
<body class="admin-console user ${bodyClass}">

<div class="header"><a href="/" class="logo router-link-active"><img src="/img/logo.9f4f97db.png"></a><div class="bg-light-blue"><ul class="exNav"><li class="exNavItem"><a href="/" class="exNavItem router-link-active">Trade</a></li><li class="exNavItem"><a class="exNavItem">Funds</a><ul class="subNav"><li class="">Balances</li><li class="">Deposits</li><li class="">Withdrawals</li><li class="">Tx History</li></ul></li><li class="exNavItem"><a class="exNavItem">Orders</a><ul class="subNav"><li class="">Open Orders</li><li class="">Order History</li><li class="">Trade History</li></ul></li></ul><div class="ibuANF fzMYEz dlpzrD"><ul class="exNav f-fr"><li class="exNavItem"><a href="/account" class="link exNavItem router-link-exact-active router-link-active">Account</a></li><li class="exNavItem"><a href="/support" class="link exNavItem">Support</a></li><li class="exNavItem"><a class="loginLogut exNavItem">Logout</a></li></ul></div></div></div>

    <div class="container">
        <div class="bs-sidebar col-sm-3">
            <ul>
                <li class="<#if active=='account'>active</#if>"><a href="${url.accountUrl}">${msg("account")}</a></li>
                <#if features.passwordUpdateSupported><li class="<#if active=='password'>active</#if>"><a href="${url.passwordUrl}">${msg("password")}</a></li></#if>
                <li class="<#if active=='totp'>active</#if>"><a href="${url.totpUrl}">${msg("authenticator")}</a></li>
                <#if features.identityFederation><li class="<#if active=='social'>active</#if>"><a href="${url.socialUrl}">${msg("federatedIdentity")}</a></li></#if>
                <li class="<#if active=='sessions'>active</#if>"><a href="${url.sessionsUrl}">${msg("sessions")}</a></li>
                <li class="<#if active=='applications'>active</#if>"><a href="${url.applicationsUrl}">${msg("applications")}</a></li>
                <#if features.log><li class="<#if active=='log'>active</#if>"><a href="${url.logUrl}">${msg("log")}</a></li></#if>
                <#if realm.userManagedAccessAllowed && features.authorization><li class="<#if active=='authorization'>active</#if>"><a href="${url.resourceUrl}">${msg("myResources")}</a></li></#if>
            </ul>
        </div>

        <div class="col-sm-9 content-area">
            <#if message?has_content>
                <div class="alert alert-${message.type}">
                    <#if message.type=='success' ><span class="pficon pficon-ok"></span></#if>
                    <#if message.type=='error' ><span class="pficon pficon-error-octagon"></span><span class="pficon pficon-error-exclamation"></span></#if>
                    ${kcSanitize(message.summary)?no_esc}
                </div>
            </#if>

            <#nested "content">
        </div>
    </div>

</body>
</html>
</#macro>