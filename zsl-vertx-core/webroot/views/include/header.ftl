[@current]
<div class="page-header navbar navbar-fixed-top">
	<!-- BEGIN HEADER INNER -->
	<div class="page-header-inner">
		<!-- BEGIN LOGO -->
		<div class="page-logo">
			<a href="${base}/admin/common/main.jhtml">
				<img src="${base}/resources/plugin/metronic/admin/layout4/img/logo-light.png" alt="logo" class="logo-default">
			</a>
			<div class="menu-toggler sidebar-toggler hide" >
				<!-- DOC: Remove the above "hide" to enable the sidebar toggler button on header -->
			</div>
		</div>
		<!-- END LOGO -->
		<!-- BEGIN RESPONSIVE MENU TOGGLER -->
		<a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse">
		</a>
		<!-- END RESPONSIVE MENU TOGGLER -->
		<!-- BEGIN PAGE TOP -->
		<div class="page-top">
			<!-- BEGIN TOP NAVIGATION MENU -->
			<div class="top-menu">
				<ul class="nav navbar-nav pull-right">
					<li class="separator hide">
					</li>
					<!-- BEGIN USER LOGIN DROPDOWN -->
					<!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
					<li class="dropdown dropdown-user dropdown-dark">
						<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
						<span class="username username-hide-on-mobile">
						${current.name} </span>
						<!-- DOC: Do not remove below empty space(&nbsp;) as its purposely used -->
						<img alt="" class="img-circle" src="[#if current.avatar??]${current.avatar}[#else]${base}/resources/admin/images/avatar.jpg[/#if]">
						</a>
						<ul class="dropdown-menu dropdown-menu-default">
							<li>
								<a href="${base}/admin/profile/edit.jhtml">
								<i class="icon-user"></i> ${message("admin.index.profile")} </a>
							</li>
							<li>
								<a href="${base}/admin/profile/password.jhtml">
								<i class="icon-settings"></i> ${message("admin.index.resetpassword")} </a>
							</li>
							<li class="divider">
							</li>
							<li>
								<a href="${base}/admin/common/lock.jhtml">
								<i class="icon-lock"></i> ${message("admin.index.lock")} </a>
							</li>
							<li>
								<a href="${base}/admin/common/logout.jhtml">
								<i class="icon-key"></i> ${message("admin.index.logout")} </a>
							</li>
						</ul>
					</li>
					<!-- END USER LOGIN DROPDOWN -->
				</ul>
			</div>
			<!-- END TOP NAVIGATION MENU -->
		</div>
		<!-- END PAGE TOP -->
	</div>
	<!-- END HEADER INNER -->
</div>
[/@current]