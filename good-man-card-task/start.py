import json
import time
import pymysql
from config import CONFIG
from task import TaskExecuter

class Task:
  def __init__(self):
    self.id = None
    self.rid = None
    self.type = None
    self.params = None
    self.state = None
    self.result = None
    self.remarks = None
    self.create_time = None
    self.update_time = None

  def from_tuple(self, tp):
    self.id = tp[0]
    self.rid = tp[1]
    self.type = tp[2]
    self.params = tp[3]
    self.state = tp[4]
    self.result = tp[5]
    self.remarks = tp[6]
    self.create_time = tp[7]
    self.update_time = tp[8]
  
  def get_dict_params(self):
    if self.params is None:
      return None
    return json.loads(self.params)
  
  
  def __str__(self) -> str:
      return f'({self.id}, {self.rid}, {self.state})'



class TaskRepository:
  def __init__(self) -> None:
    self.conn = pymysql.connect(host=CONFIG['host'], port=CONFIG['port'], user=CONFIG['username'], password=CONFIG['password'], database=CONFIG['db'])

  def fetch_one(self):
    select_sql = f'select id, rid, type, params, state,result,  remarks, create_time, update_time from gmc_task where state=0 limit 1'
    cursor = self.conn.cursor()
    num = cursor.execute(select_sql)
    if num != 0:
      row = cursor.fetchone()
      t = Task()
      t.from_tuple(row)
      print(f'fecth one task {t}')
      update_sql = f'update gmc_task set state=1 where id={t.id}'
      ret = cursor.execute(update_sql)
      self.conn.commit()
      if ret == 1:
        cursor.close()
        return t
    self.conn.commit()
    cursor.close()
    return None

  def finish(self, task):
    cursor = self.conn.cursor()
    update_sql = f'update gmc_task set state={task.state}, update_time="{task.update_time}", result="{task.result}", remarks="{task.remarks}" where id={task.id}'
    cursor.execute(update_sql)
    self.conn.commit()
    cursor.close()

  def close(self):
    self.conn.close()


def now():
  return time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())   


def main():
  task_repository = TaskRepository()
  while True:
    try:
      task = task_repository.fetch_one()
      if task is not None:
        exe = TaskExecuter(task)
        exe.execute()
        if exe.is_success():
          task.state = 2
        else:
          task.state = 3
          
        task.update_time = now()
        task.result = exe.get_result()
        task.remarks = exe.get_cause()
        task_repository.finish(task)
      else:
        print('等待新任务')
        time.sleep(1)
    except Exception as e:
      print(e)
  task_repository.close()


if __name__ == '__main__':
  main()