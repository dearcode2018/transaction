@ rem ----- ��Ϣ -----
@ rem @filename x.bat
@ rem @version 1.0
@ rem @description 
@ rem @author qye.zheng

@ rem @warning Ϊ�˷�ֹ���Ļ������룬�����ļ���ʱ��Ӧ�ñ���ΪANSI�����ʽ.
@ rem ################################################################################


@ rem ����
@ title ���� MySql ����
@ rem ########## begin  ##########

@ rem �ر���ʾ���ʹ��������ִ��ǰ����ʾ
@ rem @ echo off
@ echo off
@ rem ��������ʾ @ echo on

@ rem ----- ����������
:: ������ͣ��ʶ
set stopFlag=false

:: ���� ��ʼ����Ŀ¼
call MySqlHome.bat

:: �������� bat ֮����Ҫ�������ñ��⣬���ⱻ��һ��bat���򸲸�
@ title ���� MySql ����

@ rem ----- ���������

:: �ɶ����ſ��԰Ѷ���Ҫִ�е�����Χ������else ����� if ��֧����������ͬһ�У����� else ������Ϊ��ͬ���
if exist %MYSQL_HOME% (
cd %MYSQL_HOME%
:: ���� bin Ŀ¼
cd bin
:: ִ��mysql ��������
:: ������ǰ���� start /b �����ú���������ں�̨����
start /b mysqld -u root

:: ����ָ�������ݿ�
:: mysql -u username -pPassword db_name

:: �ر����ݿ���� (ָ����������)
:: mysqladmin -u root -pPassword shutdown
) else (
echo %MYSQL_HOME% not exists, please check!
:: ������ͣ��ʶ
set stopFlag=true
)

@ rem pause
if "true"=="%stopFlag%" (pause)
@ rem
:: �˳�
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
