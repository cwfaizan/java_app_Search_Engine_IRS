<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@include file="taglib.jsp" %>

<header class="main-header">
    <nav class="navbar navbar-static-top">
      <div class="container">
        <div class="navbar-header">
          <a href="<spring:url value="/index"/>" class="navbar-brand"><b>IR </b>System</a>
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse">
            <i class="fa fa-bars"></i>
          </button>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse pull-left" id="navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Filters <span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <!-- <li id="test"><a href="#">Test Case</a></li>
                <li class="divider"></li> -->
                <li id="filteringStemming"><a href="<spring:url value="/filters/filtered-documents"/>">Filtering & Stemming</a></li>
                <li class="divider"></li>
                <li id="tokenization"><a href="<spring:url value="/filters/tokenization"/>">Tokenization (terms)</a></li>
                <li class="divider"></li>
                <li id="invertedIndex"><a href="<spring:url value="/filters/inverted-index"/>">Inverted Index</a></li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Techniques <span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="<spring:url value="/techniques/calculate-nnn"/>">NNN</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/techniques/calculate-ntc"/>">NTC</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/techniques/calculate-atc"/>">ATC</a></li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Cos( nnn,atc )<span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="<spring:url value="/nnn-atc/1"/>">Athletics news</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/nnn-atc/2"/>">Business news</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/nnn-atc/3"/>">Cricket news</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/nnn-atc/4"/>">Entertainment news</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/nnn-atc/5"/>">Football news</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/nnn-atc/6"/>">Politics news</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/nnn-atc/7"/>">Rugby news</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/nnn-atc/8"/>">Sport news</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/nnn-atc/9"/>">Tech news</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/nnn-atc/10"/>">Tennis news</a></li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Precision-Recall<span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="<spring:url value="/nnn-atc/precision-recall/1"/>">Athletics news</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/nnn-atc/precision-recall/2"/>">Business news</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/nnn-atc/precision-recall/3"/>">Cricket news</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/nnn-atc/precision-recall/4"/>">Entertainment news</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/nnn-atc/precision-recall/5"/>">Football news</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/nnn-atc/precision-recall/6"/>">Politics news</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/nnn-atc/precision-recall/7"/>">Rugby news</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/nnn-atc/precision-recall/8"/>">Sport news</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/nnn-atc/precision-recall/9"/>">Tech news</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/nnn-atc/precision-recall/10"/>">Tennis news</a></li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Cos( ntc,atc )<span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="<spring:url value="/ntc-atc/1"/>">Athletics news</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/ntc-atc/2"/>">Business news</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/ntc-atc/3"/>">Cricket news</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/ntc-atc/4"/>">Entertainment news</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/ntc-atc/5"/>">Football news</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/ntc-atc/6"/>">Politics news</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/ntc-atc/7"/>">Rugby news</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/ntc-atc/8"/>">Sport news</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/ntc-atc/9"/>">Tech news</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/ntc-atc/10"/>">Tennis news</a></li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Precision-Recall<span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="<spring:url value="/ntc-atc/precision-recall/1"/>">Athletics news</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/ntc-atc/precision-recall/2"/>">Business news</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/ntc-atc/precision-recall/3"/>">Cricket news</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/ntc-atc/precision-recall/4"/>">Entertainment news</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/ntc-atc/precision-recall/5"/>">Football news</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/ntc-atc/precision-recall/6"/>">Politics news</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/ntc-atc/precision-recall/7"/>">Rugby news</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/ntc-atc/precision-recall/8"/>">Sport news</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/ntc-atc/precision-recall/9"/>">Tech news</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/ntc-atc/precision-recall/10"/>">Tennis news</a></li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">MAP<span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="<spring:url value="/map/nnn-atc"/>">NNN,ATC</a></li>
                <li class="divider"></li>
                <li><a href="<spring:url value="/map/ntc-atc"/>">NTC,ATC</a></li>
              </ul>
            </li>
          </ul>
        </div>
        <!-- /.navbar-collapse -->
        <!-- /.navbar-custom-menu -->
        <div class="navbar-custom-menu">
          <ul class="nav navbar-nav">
            <!-- User Account Menu -->
            <li class="dropdown user user-menu">
              <!-- Menu Toggle Button -->
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                <!-- The user image in the navbar-->
                <img src="<spring:url value="/resources/dist/img/faizan.JPG"/>" class="user-image" alt="User Image">
                <!-- hidden-xs hides the username on small devices so only the image appears. -->
                <span class="hidden-xs">Faizan ABBAS</span>
              </a>
              <ul class="dropdown-menu">
                <!-- The user image in the menu -->
                <li class="user-header">
                  <img src="<spring:url value="/resources/dist/img/faizan.JPG"/>" class="img-circle" alt="User Image">
                  <p>
                    Faizan ABBAS - IRS Assignment
                    <small>MF 16-21</small>
                  </p>
                </li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
      <!-- /.container-fluid -->
    </nav>
  </header>