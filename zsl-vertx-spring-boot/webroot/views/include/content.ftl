<div class="page-content-wrapper">
		<div class="page-content" style="min-height:812px">
			<div class="page-head">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
					<h1><i class="icon-grid" style="font-size: 20px;"></i>&nbsp;${message("admin.index.title")}   </h1>
				</div>
				<!-- END PAGE TITLE -->
				[#include "/admin/include/setting.ftl"]
			</div>
			<!-- BEGIN PAGE CONTENT-->
			<div class="row">
				<div class="col-md-12">
					<div class="note note-success">
						<h4 class="block"><span aria-hidden="true" class="icon-user"></span> &nbsp;&nbsp;[@shiro.principal /]</h4>
						<p>
							${message("admin.index.welcome")}	
						</p>
					</div>
				</div>
			</div>
			
			<div class="portlet light bordered">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-equalizer font-green-haze"></i>
						<span class="caption-subject font-green-haze bold uppercase">${message("admin.index.about")}	</span>
						<span class="caption-helper">${message("admin.index.about.info")}</span>
					</div>
					<div class="tools">
					</div>
				</div>
				<div class="portlet-body form">
					<!-- BEGIN FORM-->
					<form class="form-horizontal" role="form">
						<div class="form-body">
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label col-md-4">${message("admin.index.systemName")}:</label>
										<div class="col-md-8">
											<p class="form-control-static">
												${systemName}
											</p>
										</div>
									</div>
								</div>
								<!--/span-->
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label col-md-4">${message("admin.index.systemVersion")}:</label>
										<div class="col-md-8">
											<p class="form-control-static">
												 ${systemVersion}
											</p>
										</div>
									</div>
								</div>
								<!--/span-->
							</div>
							<!--/row-->
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label col-md-4">${message("admin.index.javaVersion")}:</label>
										<div class="col-md-8">
											<p class="form-control-static">
												${javaVersion}
											</p>
										</div>
									</div>
								</div>
								<!--/span-->
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label col-md-4">${message("admin.index.osName")}:</label>
										<div class="col-md-8">
											<p class="form-control-static">
												${osName}  ${osArch}
											</p>
										</div>
									</div>
								</div>
								<!--/span-->
							</div>
							<!--/row-->
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label col-md-4">${message("admin.index.serverInfo")}:</label>
										<div class="col-md-8">
											<p class="form-control-static">
												${serverInfo}
											</p>
										</div>
									</div>
								</div>
								<!--/span-->
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label col-md-4">${message("admin.index.servletVersion")}:</label>
										<div class="col-md-8">
											<p class="form-control-static">
												 ${servletVersion}
											</p>
										</div>
									</div>
								</div>
								<!--/span-->
							</div>
							<!--/row-->
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label class="control-label col-md-2">${message("admin.index.javaHome")}:</label>
										<div class="col-md-10">
											<p class="form-control-static">
												${javaHome}
											</p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</form>
					<!-- END FORM-->
				</div>
			</div>
			<!-- END PAGE CONTENT-->
		</div>
	</div>