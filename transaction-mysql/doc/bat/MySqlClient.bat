@ rem ----- ��Ϣ -----
@ rem @filename MySqlClient.bat
@ rem @version 1.0
@ rem @author qye.zheng
@ rem @description MySql �ͻ���

@ rem @warning Ϊ�˷�ֹ���Ļ������룬�����ļ���ʱ��Ӧ�ñ���ΪANSI�����ʽ.
@ rem ################################################################################


:: ��ȡbat�ļ����ڵĴ��������������� C:
set currentDriver=%~dp0
set currentDriver=%currentDriver:~0,2%
:: ִ��������������ű������뵱ǰ�ű����ڵ�·��
%currentDriver%
cd %~dp0

:: ���� ��ʼ����Ŀ¼
call MySqlHome.bat

@ rem ���� 
@ title MySql �ͻ���
@ rem ########## begin  ##########

@ rem �ر���ʾ���ʹ��������ִ��ǰ����ʾ
@ rem @ echo off
@ echo off
@ rem ��������ʾ @ echo on

@ rem ----- ����������


@ rem ----- ���������

:: �ɶ����ſ��԰Ѷ���Ҫִ�е�����Χ������else ����� if ��֧����������ͬһ�У����� else ������Ϊ��ͬ���
if exist %MYSQL_HOME% (
cd %MYSQL_HOME%
:: ���� bin Ŀ¼
cd bin

mysql -uroot -proot
:: �������ֻ����mysql������ִ��
:: use transaction


@ rem pause

@ rem

@ rem
@ rem �����ʾ��Ϣ

::
:: 1) 
:: 2)
:: 3)
:: 4)
:: 5)
:: 6)
:: 7)
:: 8)
:: 9)
:: 10)

:: �ڳ����ĩβ�����Ը���ִ�еĽ��(�ɹ���ʧ��) ������ʾ��Ϣ���ɹ�����ֱ��ִ��exit����ʧ��
:: ����ִ��pause��Ȼ�����ͨ������̨�����Ϣ�����ԡ���λ����.
:: �����ڳ���������һ���ɹ���ʧ�ܵı�־-����ֵ���������������ִ������.

@ rem echo
@ rem exit
@ rem ########## end of ##########

@ rem ע��˵��: @ rem ע������  ���� :: ע������
@ rem rem ������ð�� ���� ������дע��
