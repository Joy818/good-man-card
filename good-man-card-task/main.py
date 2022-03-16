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
    self.remarks = None
    self.create_time = None
    self.update_time = None

  def from_tuple(self, tp):
    self.id = tp[0]
    self.rid = tp[1]
    self.type = tp[2]
    self.params = tp[3]
    self.state = tp[4]
    self.remarks = tp[5]
    self.create_time = tp[6]
    self.update_time = tp[7]
  
  def get_dict_params(self):
    if self.params is None:
      return None
    return json.loads(self.params)
  
  
  def __str__(self) -> str:
      return f'({self.id}, {self.rid}, {self.state})'



class TaskRepository:

  def __init__(self, conn) -> None:
    self.conn = conn
    self.cursor = conn.cursor()


  def fetch_one(self):
    select_sql = f'select id, rid, type, params, state, remarks, create_time, update_time from gmc_task where state=0 limit 1'
    num = self.cursor.execute(select_sql)
    if num != 0:
      row = self.cursor.fetchone()
      t = Task()
      t.from_tuple(row)
      print(f'fecth one task {t}')
      update_sql = f'update gmc_task set state=1 where id={t.id}'
      ret = self.cursor.execute(update_sql)
      self.conn.commit()
      if ret == 1:
        return t
    return None

  def finish(self, task):
    update_sql = f'update gmc_task set state={task.state}, update_time="{task.update_time}" {"remarks="+str(task.remarks) if task.remarks is not None else "" } where id={task.id}'
    self.cursor.execute(update_sql)
    self.conn.commit()

  def close(self):
    self.cursor.close()


def now():
  return time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())   


def main():
  conn = pymysql.connect(host=CONFIG['host'], port=CONFIG['port'], user=CONFIG['username'], password=CONFIG['password'], database=CONFIG['db'])

  task_repository = TaskRepository(conn)
  try:
    while True:
      task = task_repository.fetch_one()
      if task is not None:
        exe = TaskExecuter(task)
        exe.execute()
        if exe.is_success():
          task.state = 2
          task.update_time = now()
        else:
          task.state = 3
          task.update_time = now()
          task.remarks = exe.cuase
        task_repository.finish(task)
      else:
        print('等待新任务')
        time.sleep(1)
  finally:
    task_repository.close()
    conn.close()


if __name__ == '__main__':
  main()